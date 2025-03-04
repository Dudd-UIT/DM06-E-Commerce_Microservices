package com.microservices.ecommerce.payment;

import java.math.BigDecimal;

import com.microservices.ecommerce.customer.Customer;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer) {
}