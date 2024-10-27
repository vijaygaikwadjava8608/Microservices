package com.priceman.PaymentService.service;

import com.priceman.PaymentService.model.PaymentResponse;
import com.priceman.PaymentService.model.PaymentRequest;


public interface PaymentService {


    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
