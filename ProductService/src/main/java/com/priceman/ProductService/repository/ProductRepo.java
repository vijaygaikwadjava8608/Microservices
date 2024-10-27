package com.priceman.ProductService.repository;

import com.priceman.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Transactional
    @Modifying
    @Query("update Product p set p.quantity = ?1 where p.productId = ?2")
    int reducedProductQuantity(long quantity, long productId);
}
