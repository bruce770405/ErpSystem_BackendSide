package com.bruce.erpapp.service;

import com.bruce.erpapp.service.common.*;

/**
 * 維修單service.
 */
public interface OrderService {

    /**
     * 創建一筆新的orderId給前端.
     *
     * @return
     */
    String queryOrderId();

    /**
     * @param rq
     */
    OrderServiceRs saveOrder(OrderServiceRq rq);

    /**
     * @param rq
     * @return
     */
    OrderServiceQueryRs queryOrder(OrderServiceQueryRq rq);

    /**
     * @param rq
     * @return
     */
    OrderServiceQueryRs queryOrders(OrderServiceQueryRq rq);

    /**
     * @param rq
     */
    void updateOrder(OrderServiceUpdateRq rq);
}
