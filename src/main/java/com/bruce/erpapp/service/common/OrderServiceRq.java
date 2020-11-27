package com.bruce.erpapp.service.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderServiceRq {

    private String orderId;

    private String custName;

    private String phone;

    private String about;

    private BigDecimal amount;

    private String device;

    private String deviceColor;

    private String gender;

    private String errorDesc;

    private String pin;

    private String status;

    private String imei;
}
