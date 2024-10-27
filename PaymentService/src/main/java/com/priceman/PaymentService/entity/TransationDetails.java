package com.priceman.PaymentService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TRANSATION_DETAILS")
public class TransationDetails {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(name = "ORDER_ID")
private long orderId;
@Column(name = "PAYMENT_MODE")
private String paymentMode;
@Column(name = "REFERENCE_NUM")
private String referenceNumber;
@Column(name = "PAYMENT_DATE")
private Instant paymentDate;
@Column(name = "STATUS")
private String paymentStatus;
@Column(name = "AMOUNT")
private long amount;



}
