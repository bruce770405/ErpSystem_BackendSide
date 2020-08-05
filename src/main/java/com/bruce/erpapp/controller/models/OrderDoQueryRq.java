package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.RequestData;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDoQueryRq extends RequestData {

    private String startDate;

    private String endDate;

    private String orderId;

    private String custName;

    private String phone;

    private String status;

}
