package com.priceman.OrderService.config;

import com.priceman.OrderService.external.client.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class feignConfig {

    @Bean
    ErrorDecoder errorDecoder(){
        return  new CustomErrorDecoder();
    }

}
