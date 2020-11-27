package com.bruce.erpapp.service;

import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.bruce.erpapp.service.bean.OrderBean;
import com.bruce.erpapp.service.common.*;

import java.util.List;

/**
 * 維修單service.
 */
public interface OrderService {

    String queryOrderId();

    OrderServiceRs saveOrder(OrderServiceRq rq) throws SystemException;

    List<OrderBean> queryOrder(OrderServiceQueryBo rq);

    List<OrderBean> queryOrders(OrderServiceQueryBo rq);

    void updateOrder(OrderServiceUpdateBo rq) throws SystemException;
}
