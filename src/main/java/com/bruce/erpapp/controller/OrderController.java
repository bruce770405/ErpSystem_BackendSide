package com.bruce.erpapp.controller;

import com.bruce.erpapp.common.emums.OrderFixStatus;
import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.bruce.erpapp.common.utils.ConvertPlusUtils;
import com.bruce.erpapp.controller.models.*;
import com.bruce.erpapp.service.OrderService;
import com.bruce.erpapp.service.common.OrderServiceQueryBo;
import com.bruce.erpapp.service.common.OrderServiceRq;
import com.bruce.erpapp.service.common.OrderServiceUpdateBo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/query")
    public OrderDoQueryRs query(@RequestBody OrderDoQueryRq viewForm) {
        var response = new OrderDoQueryRs();
        var serviceQueryBo = new OrderServiceQueryBo();
        serviceQueryBo.setEndDate(StringUtils.isEmpty(viewForm.getEndDate()) ? null : LocalDate.parse(viewForm.getEndDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        serviceQueryBo.setStartDate(StringUtils.isEmpty(viewForm.getStartDate()) ? null : LocalDate.parse(viewForm.getStartDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        serviceQueryBo.setCustName(viewForm.getCustName());
        serviceQueryBo.setOrderId(viewForm.getOrderId());
        serviceQueryBo.setPhone(viewForm.getPhone());
        serviceQueryBo.setStatus(OrderFixStatus.findByCode(viewForm.getStatus()));
        var orderBeanList = orderService.queryOrders(serviceQueryBo);
        response.setOrderBeanList(orderBeanList);
        return response;
    }

    @GetMapping("/query/{orderKey}")
    public OrderDoQueryOneRs queryOneItem(@PathVariable("orderKey") String orderKxy) {
        var response = new OrderDoQueryOneRs();
        var rq = new OrderServiceQueryBo();
        rq.setOrderId(orderKxy);
        var rs = orderService.queryOrder(rq);
        response.setOrder(rs.get(0));
        return response;
    }

    @PostMapping("/add")
    public OrderDoSaveRs addOne(@RequestBody OrderDoSaveRq viewForm) throws SystemException {
        var rq = new OrderServiceRq();
        rq.setPhone(viewForm.getPhone());
        rq.setAbout(viewForm.getMemo());
        rq.setAmount(ConvertPlusUtils.str2BigDecimal(viewForm.getFixAmount()));
        rq.setDevice(viewForm.getDevice());
        rq.setDeviceColor(viewForm.getColor());
        rq.setGender(viewForm.getGender());
        rq.setImei(viewForm.getImei());
        rq.setCustName(viewForm.getCustName());
        rq.setErrorDesc(viewForm.getErrorDesc());
        rq.setPin(viewForm.getDevicePin());
        var rs = orderService.saveOrder(rq);

        var response = new OrderDoSaveRs();
        response.setOrderId(rs.getOrderId());
        response.setDate(rs.getDate());
        response.setTime(rs.getTime());
        return response;
    }

    @PostMapping("/update")
    public OrderDoUpdateRs update(@RequestBody OrderDoUpdateRq viewForm) throws SystemException {
        var rq = new OrderServiceUpdateBo();
        rq.setPhone(viewForm.getPhone());
        rq.setOrderId(viewForm.getOrderId());
        rq.setMemo(viewForm.getMemo());
        rq.setAmount(ConvertPlusUtils.str2BigDecimal(viewForm.getFixAmount()));
        rq.setDevice(viewForm.getDevice());
        rq.setDeviceColor(viewForm.getColor());
        rq.setGender(viewForm.getGender());
        rq.setCustName(viewForm.getCustName());
        rq.setErrorDesc(viewForm.getErrorDesc());
        rq.setPin(viewForm.getDevicePin());
        rq.setStatus(viewForm.getStatusCode());
        orderService.updateOrder(rq);
        return new OrderDoUpdateRs();
    }

}
