package com.bruce.erpapp.controller.models;

import com.bruce.erpapp.common.models.ResponseData;
import com.bruce.erpapp.service.bean.OrderBean;
import lombok.Data;

@Data
public class OrderDoQueryOneRs extends ResponseData {
    private OrderBean order;
}
