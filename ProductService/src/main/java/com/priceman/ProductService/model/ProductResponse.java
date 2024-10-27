package com.priceman.ProductService.model;

import jakarta.persistence.Column;
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
