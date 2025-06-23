package com.xpay.common.dto;

@lombok.Data
public class PaymentRequest {
    private String merchantId;
    private Double amount;
}