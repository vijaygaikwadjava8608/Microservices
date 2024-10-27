package com.priceman.PaymentService.service;

import com.priceman.PaymentService.entity.TransationDetails;
import com.priceman.PaymentService.model.PaymentMode;
import com.priceman.PaymentService.model.PaymentResponse;
import com.priceman.PaymentService.model.PaymentRequest;
import com.priceman.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    /**
     * @param paymentRequest
     * @return
     */

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;


    @Override
    public long doPayment(PaymentRequest paymentRequest) {
       log.info("Recording Payment Details: {} ",paymentRequest);
        TransationDetails transationDetails=TransationDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .referenceNumber(paymentRequest.getReferenceNumber())
                .orderId(paymentRequest.getOrderId())
                .build();

        transactionDetailsRepository.save(transationDetails);
        log.info("Transaction completed with id: {} ",transationDetails.getId());


        return transationDetails.getId();
    }

    /**
     * @param orderId
     * @return
     */
    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting payment details for the order id: {} ",orderId);
        TransationDetails transationDetails=transactionDetailsRepository.findByOrderId(orderId);


        return PaymentResponse
                .builder()
                .paymentId(transationDetails.getId())
                .paymentMode(PaymentMode.valueOf(transationDetails.getPaymentMode()))
                .paymentDate(transationDetails.getPaymentDate())
                .status(transationDetails.getPaymentStatus())
                .amount(transationDetails.getAmount())
                .build();
    }
}
