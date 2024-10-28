Overview
This project is a collection of microservices built using Spring Boot 3.4, incorporating the latest approaches for service development. The microservices include Order Service, Product Service, Payment Service, and more, each designed to handle specific business logic independently.

Table of Contents
Getting Started

Microservices

Configuration

Dependencies

Running the Project

API Endpoints

Contributing

License

Getting Started
Prerequisites
Java 17+

Maven 3.6+

Docker (for containerized services)

Cloning the Repository
bash

Copy
git clone https://github.com/vijaygaikwadjava8608/Microservices.git
cd Microservices
Microservices
Order Service
Handles order creation and retrieval.

Port: 8081

Endpoints:

POST /order/placeOrder

GET /order/{orderId}

Product Service
Manages product information.

Port: 8082

Endpoints:

GET /product/{productId}

Payment Service
Processes payments for orders.

Port: 8083

Endpoints:

POST /payment

GET /payment/order/{orderId}

Configuration
The project uses application.yaml for configuration, with environment-specific overrides.

application.yaml Example
yaml

Copy
server:
  port: 8081
spring:
  application:
    name: "ORDER-SERVICE"
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:5432/order_db
    username: testuser
    password: testuser
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        default_schema: sch_order
Dependencies
Major Dependencies
Spring Boot

Spring Cloud

Spring Data JPA

Spring Security

Resilience4j

Feign Client

Lombok

Running the Project
Using Maven
Navigate to each service directory and run:

bash

Copy
mvn spring-boot:run
Using Docker
bash

Copy
docker-compose up
API Endpoints
Detailed API documentation is provided for each service within the respective service directories.

Contributing
Fork the repository.

Create a new branch (git checkout -b feature-foo).

Commit your changes (git commit -am 'Add feature foo').

Push to the branch (git push origin feature-foo).

Create a new Pull Request.

License
This project is licensed under the MIT License. See the LICENSE file for details.
