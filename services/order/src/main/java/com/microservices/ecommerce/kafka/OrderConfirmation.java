package com.microservices.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.microservices.ecommerce.customer.CustomerResponse;
import com.microservices.ecommerce.order.PaymentMethod;
import com.microservices.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}