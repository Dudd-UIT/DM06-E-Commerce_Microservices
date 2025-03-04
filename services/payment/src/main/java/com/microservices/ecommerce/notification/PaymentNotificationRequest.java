package com.microservices.ecommerce.notification;

import java.math.BigDecimal;

import com.microservices.ecommerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail) {
}