package com.priceman.OrderService.service;

import com.priceman.OrderService.model.OrderRequest;
import com.priceman.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
