package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.ResponseData;
import com.bruce.erpapp.service.bean.OrderBean;
import lombok.Data;

import java.util.List;

@Data
public class OrderDoQueryRs extends ResponseData {

    private String orderId;


    private List<OrderBean> orderBeanList;
}
