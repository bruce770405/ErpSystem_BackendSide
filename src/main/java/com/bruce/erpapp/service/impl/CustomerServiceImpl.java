package com.bruce.erpapp.service.impl;

import com.bruce.erpapp.service.CustomerService;
import com.bruce.erpapp.service.common.CustomerServiceRq;
import com.bruce.erpapp.service.common.CustomerServiceRs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service(value = "CustomerServiceImpl")
@Primary
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Long saveCustomer(CustomerServiceRq rq) {
        return null;
    }

    @Override
    public CustomerServiceRs queryCustomer(CustomerServiceRq rq) {
        return null;
    }

    @Override
    public CustomerServiceRs updateCustomer(CustomerServiceRq rq) {
        return null;
    }
}