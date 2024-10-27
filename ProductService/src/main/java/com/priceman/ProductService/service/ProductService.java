package com.priceman.ProductService.service;

import com.priceman.ProductService.model.ProductRequest;
import com.priceman.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
