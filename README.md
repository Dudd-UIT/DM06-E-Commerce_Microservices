# Spring Boot Microservices Architecture
This repository contains a Spring Boot-based microservices architecture designed to manage customer orders, products, payments, and notifications. The system follows best practices for building distributed applications and ensures scalability, reliability, and maintainability.

## Overview
The system consists of multiple microservices working together to provide an end-to-end solution. The architecture is designed around a microservices pattern using Spring Boot, Kafka for message brokering, MongoDB for data storage, and Zipkin for distributed tracing.
## Microservices
Customer Service: Manages customer details.
Product Service: Handles product details and inventory.
Order Service: Manages customer orders and order-related actions.
Payment Service: Manages payment verification and updates.
Notification Service: Sends notifications to users based on events such as order status or payment confirmation.
## Key Features:
  - User Registration & Authentication: Users can register and authenticate securely.
  - Product Management: Users can manage their products and make orders.
  - Order Management: Includes functionality for creating, updating, and managing customer orders.
  - Payment Processing: Ensures secure payment verification and confirmation.
  - Notifications: Sends notifications to users when order and payment statuses change.
  - Asynchronous Processing: Utilizes Kafka as a message broker to handle asynchronous tasks like sending order and payment confirmations.
  - Distributed Tracing: Zipkin is integrated for tracking requests across different microservices for monitoring and debugging.
## Architecture Diagram
![z6349853359004_583b91fc1d04b6166f04a695ab4a96db](https://github.com/user-attachments/assets/ac8cf9f3-5aff-4f56-896a-8b6f7e1e1653)
## Technologies Used:
  - Spring Boot (for building microservices)
  - Spring Security, KeyCloak (for authentication and security)
  - Kafka (for asynchronous message brokering)
  - MongoDB (for storing product and customer data)
  - Eureka (for service discovery)
  - Zipkin (for distributed tracing)
  - Docker (for containerization of services)
