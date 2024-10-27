package com.priceman.CloudeGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {


    @GetMapping("orderServiceFallBack")
    public String orderServiceFallBack(){
        return "order service is down";
    }



    @GetMapping("paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "payment service is down";
    }


    @GetMapping("productServiceFallBack")
    public String productServiceFallBack(){
        return "product Service is down";
    }

}
