package com.bruce.erpapp.controller;

import com.bruce.erpapp.controller.models.CustomerDoQueryRq;
import com.bruce.erpapp.controller.models.CustomerDoQueryRs;
import com.bruce.erpapp.controller.models.CustomerDoSaveRq;
import com.bruce.erpapp.controller.models.CustomerDoSaveRs;
import com.bruce.erpapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService companyService;

    @PostMapping("/query")
    public CustomerDoQueryRs query(@RequestBody CustomerDoQueryRq viewForm) {
        var response = new CustomerDoQueryRs();

        return response;
    }

    @PostMapping("/add")
    public CustomerDoSaveRs addOne(@RequestBody CustomerDoSaveRq viewForm) {
        var response = new CustomerDoSaveRs();

        return response;
    }


}
