package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.RequestData;
import lombok.Data;

import java.util.List;

@Data
public class OrderDoUpdateRq extends RequestData {

    private String orderId;

    private String custName;

    private String phone;

    private String fixAmount;

    private String date;

    private String device;

    private String color;

    private String gender;

    private String errorDesc;

    /**
     * 備註.
     */
    private String memo;

    private String devicePin;

    private String statusCode;
}
