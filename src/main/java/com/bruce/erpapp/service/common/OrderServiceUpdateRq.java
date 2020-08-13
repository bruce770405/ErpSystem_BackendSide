package com.bruce.erpapp.service.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderServiceUpdateRq {

    private String orderId;

    private String custName;

    private String phone;

    private BigDecimal amount;

    private String device;

    private String deviceColor;

    private String gender;

    private String errorDesc;

    private String pin;

    private String memo;

    private String status;

}
