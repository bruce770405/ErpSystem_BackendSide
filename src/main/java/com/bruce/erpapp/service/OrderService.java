package com.bruce.erpapp.service;

import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.bruce.erpapp.service.common.*;

/**
 * 維修單service.
 */
public interface OrderService {

    String queryOrderId();

    OrderServiceRs saveOrder(OrderServiceRq rq) throws SystemException;

    OrderServiceQueryRs queryOrder(OrderServiceQueryRq rq);

    OrderServiceQueryRs queryOrders(OrderServiceQueryRq rq);

    void updateOrder(OrderServiceUpdateRq rq) throws SystemException;
}
