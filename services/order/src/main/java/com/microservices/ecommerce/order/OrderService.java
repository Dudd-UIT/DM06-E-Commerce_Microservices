package com.microservices.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.ecommerce.customer.CustomerClient;
import com.microservices.ecommerce.exception.BusinessException;
import com.microservices.ecommerce.kafka.OrderConfirmation;
import com.microservices.ecommerce.kafka.OrderProducer;
import com.microservices.ecommerce.orderline.OrderLineRequest;
import com.microservices.ecommerce.orderline.OrderLineService;
import com.microservices.ecommerce.payment.PaymentClient;
import com.microservices.ecommerce.payment.PaymentRequest;
import com.microservices.ecommerce.product.ProductClient;
import com.microservices.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exist with the provided ID"));
        // purchase the products --> product-ms
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        // persist order
        var order = this.repository.save(mapper.toOrder(request));
        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            this.orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity())

            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(), order.getId(), order.getReference(), customer);

        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation -- notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(), request.amount(), request.paymentMethod(), customer, purchasedProducts));
        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with the provided ID: %d", id)));
    }
}