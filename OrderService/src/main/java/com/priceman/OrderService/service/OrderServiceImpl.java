package com.priceman.OrderService.service;

import com.priceman.OrderService.entity.Order;
import com.priceman.OrderService.exception.CustomException;
import com.priceman.OrderService.external.client.PaymentService;
import com.priceman.OrderService.external.client.ProductService;
import com.priceman.OrderService.external.client.request.PaymentRequest;
import com.priceman.OrderService.external.client.response.PaymentResponse;
import com.priceman.OrderService.external.client.response.ProductResponse;
import com.priceman.OrderService.model.OrderRequest;
import com.priceman.OrderService.model.OrderResponse;
import com.priceman.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> Save the data with Status order created
        //product Service - Block Product (Reduced Quantity)
        //Payment Service -> Payment -> Success -> COMPLETE , else CANCELLED
        log.info("Product service calling");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("product service call end");

        log.info("placing Order request {}",orderRequest);
        Order order=Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order= orderRepository.save(order);
        log.info("Calling payment service to complete payment");

        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus=null;
        try{
            paymentService.doPaymemnt(paymentRequest);
            log.info("Payment done successfully. Changing the order status to PLACED");
            orderStatus="PLACED";
        }
        catch (Exception e){
            log.error("Error occurred  in payment. Changing the order status to PAYMENT_FAILED");
            orderStatus="PAYMENT_FAILED";
        }
        log.info("update the order status");
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);


        log.info("order is placed successfully with Order Id {}:",order.getId());



        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {

        log.info("get Order details for order id : {}",orderId);
        Order order= orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order is not found for order id : "+orderId,"NOT_FOUND ",404));


        log.info("invoking the product service to fetch date for product id : "+order.getProductId());
       //instead of using feign client use restTemplate for calling other micro services
        ProductResponse productResponse =  restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);
        log.info("Getting payment information form the payment Service");
        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(),PaymentResponse.class);

        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();
        OrderResponse.PaymentDetails paymentDetails
                =OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .amount(paymentResponse.getAmount())
                .orderId(paymentResponse.getOrderId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .build();


        OrderResponse orderResponse=OrderResponse
                .builder()
                .orderId(order.getId())
                .orderstatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();


        log.info("Got order : {}",orderResponse.toString());



        return orderResponse;
    }
}
