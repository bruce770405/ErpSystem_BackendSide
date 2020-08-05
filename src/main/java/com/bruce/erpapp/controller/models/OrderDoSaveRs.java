package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.ResponseData;
import lombok.Data;

@Data
public class OrderDoSaveRs extends ResponseData {

    private String orderId;

    private String date;

    private String time;
}
