package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.RequestData;
import lombok.Data;

@Data
public class OrderDoSaveRq extends RequestData {

    private String custName;

    private String phone;

    private String memo;

    private String fixAmount;

    private String device;

    private String color;

    private String gender;

    private String imei;

    private String errorDesc;

    private String devicePin;

}
