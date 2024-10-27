package com.priceman.ProductService.service;

import com.priceman.ProductService.entity.Product;
import com.priceman.ProductService.exception.ProductException;
import com.priceman.ProductService.model.ProductRequest;
import com.priceman.ProductService.model.ProductResponse;
import com.priceman.ProductService.repository.ProductRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public long addProduct(ProductRequest productRequest) {
       log.info("Adding product ...");

        Product product=Product
                .builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        Product result=productRepo.save(product);
        log.info("product is created");
        return result.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product details for productId: {}",productId);
        Product product=productRepo.findById(productId).orElseThrow(()->new ProductException("Product with give product Id is not found","PRODUCT_NOT_FOUND"));

        //builder approch
//        ProductResponse productResponse= ProductResponse
//                .builder()
//                .productName(product.getProductName())
//                .quantity(product.getQuantity())
//                .productId(product.getProductId())
//                .price(product.getPrice())
//                .build();
        ProductResponse productResponse = new ProductResponse();
                copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduced product productId : {}, and Quantity : {}",productId,quantity);

        Product product=productRepo.findById(productId).orElseThrow(()->new ProductException("Product with given id not found","PRODUCT_NOT_FOUND"));

        if(product.getQuantity()<quantity){
            throw new ProductException("Product does not have sufficient Quantity","INSUFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepo.save(product);
        log.info("Product Quantity update successfully");
    }
}
