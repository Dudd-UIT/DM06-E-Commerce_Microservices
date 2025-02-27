package com.microservices.ecommerce.payment;

import java.math.BigDecimal;

import com.microservices.ecommerce.customer.CustomerResponse;
import com.microservices.ecommerce.order.PaymentMethod;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer) {
}
