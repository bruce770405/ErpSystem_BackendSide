package com.bruce.erpapp.service;

import com.bruce.erpapp.service.common.CustomerServiceRq;
import com.bruce.erpapp.service.common.CustomerServiceRs;

public interface CustomerService {

    Long saveCustomer(CustomerServiceRq rq);

    CustomerServiceRs queryCustomer(CustomerServiceRq rq);

    CustomerServiceRs updateCustomer(CustomerServiceRq rq);
}
