package com.priceman.OrderService.external.client;

import com.priceman.OrderService.exception.CustomException;
import com.priceman.OrderService.external.client.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(
        name = "external", fallbackMethod = "fallback"
)
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping
    public ResponseEntity<Long> doPaymemnt(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e){
        throw  new CustomException("Payment Service is not available","UNAVAIALABLE",500);
    }
}
