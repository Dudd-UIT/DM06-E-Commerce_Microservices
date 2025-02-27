package com.microservices.ecommerce.orderline;

public record OrderLineResponse(
        Integer id,
        double quantity) {
}