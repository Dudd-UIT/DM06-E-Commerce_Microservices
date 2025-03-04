package com.microservices.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.microservices.ecommerce.kafka.customer.Customer;
import com.microservices.ecommerce.kafka.payment.PaymentMethod;
import com.microservices.ecommerce.kafka.product.Product;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}