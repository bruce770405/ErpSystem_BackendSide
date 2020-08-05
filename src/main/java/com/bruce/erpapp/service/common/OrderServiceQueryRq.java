package com.bruce.erpapp.service.common;

import com.bruce.erpapp.common.emums.OrderFixStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderServiceQueryRq {

    private String orderId;

    private String custName;

    private String phone;

    private Date startDate;

    private Date endDate;

    private OrderFixStatus status;


}
