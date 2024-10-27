package com.priceman.OrderService.external.client.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productName;
    private long productId;
    private long quantity;
    private long price;

}
