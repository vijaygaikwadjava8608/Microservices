package com.priceman.ProductService.controller;


import com.priceman.ProductService.model.ProductRequest;
import com.priceman.ProductService.model.ProductResponse;
import com.priceman.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
    long productId= productService.addProduct(productRequest);

    return new ResponseEntity<Long>(productId, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(name = "id") long productId){
        ProductResponse productResponse=productService.getProductById(productId);
        return  new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){



        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
