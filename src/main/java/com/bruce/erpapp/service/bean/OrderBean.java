package com.bruce.erpapp.service.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class OrderBean {

    private String orderId;

    private String custName;

    private String phone;

    private String gender;

    private String device;

    private String imei;

    private String color;

    private String devicePin;

    private BigDecimal fixAmount;

    private String errorDesc;

    private String memo;

    private Date createTime;

    private String updateTime;

    private String status;

}
