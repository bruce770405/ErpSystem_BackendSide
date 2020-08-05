package com.bruce.erpapp.service;

import com.bruce.erpapp.service.common.CustomerServiceRq;
import com.bruce.erpapp.service.common.CustomerServiceRs;

public interface CustomerService {

    /**
     * 新增一筆客戶，並回傳id.
     * @param rq
     * @return
     */
    Long saveCustomer(CustomerServiceRq rq);

    CustomerServiceRs queryCustomer(CustomerServiceRq rq);

    CustomerServiceRs updateCustomer(CustomerServiceRq rq);
}
