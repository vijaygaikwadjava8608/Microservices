package com.priceman.PaymentService.repository;

import com.priceman.PaymentService.entity.TransationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransationDetails,Long> {

TransationDetails findByOrderId(long orderId);

}
