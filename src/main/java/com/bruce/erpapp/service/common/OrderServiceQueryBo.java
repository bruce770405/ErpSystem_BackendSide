package com.bruce.erpapp.service.common;

import com.bruce.erpapp.common.emums.OrderFixStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderServiceQueryBo {

    private String orderId;

    private String custName;

    private String phone;

    private LocalDate startDate;

    private LocalDate endDate;

    private OrderFixStatus status;

}
