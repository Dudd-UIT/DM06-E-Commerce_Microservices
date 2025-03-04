package com.microservices.ecommerce.kafka.customer;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email) {

}