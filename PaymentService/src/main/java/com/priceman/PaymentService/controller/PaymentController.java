package com.priceman.PaymentService.controller;

import com.priceman.PaymentService.model.PaymentResponse;
import com.priceman.PaymentService.model.PaymentRequest;
import com.priceman.PaymentService.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Log4j2
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPaymemnt(@RequestBody PaymentRequest paymentRequest){

        return new ResponseEntity<Long>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId){
      log.info("Payment api called for getPaymentDetailsByOrderId");

        return new ResponseEntity<PaymentResponse>(paymentService.getPaymentDetailsByOrderId(orderId),HttpStatus.OK);
    }




}
