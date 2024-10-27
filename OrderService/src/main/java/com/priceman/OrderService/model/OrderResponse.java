package com.priceman.OrderService.model;


import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private String orderstatus;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductDetails {

        private String productName;
        private long productId;
        private long quantity;
        private long price;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentDetails {

        private long paymentId;
        private String paymentStatus;
        private PaymentMode paymentMode;
        private long amount;
        private Instant paymentDate;
        private long orderId;

    }


}
