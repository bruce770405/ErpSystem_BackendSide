package com.bruce.erpapp.service.common;

import com.bruce.erpapp.service.bean.OrderBean;
import lombok.Data;

import java.util.List;

@Data
public class OrderServiceQueryRs {

    private List<OrderBean> orderBeanList;
}
