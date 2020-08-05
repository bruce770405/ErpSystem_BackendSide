package com.bruce.erpapp.controller;

import com.bruce.erpapp.common.emums.OrderFixStatus;
import com.bruce.erpapp.common.utils.ConvertPlusUtils;
import com.bruce.erpapp.controller.models.*;
import com.bruce.erpapp.service.OrderService;
import com.bruce.erpapp.service.common.OrderServiceQueryRq;
import com.bruce.erpapp.service.common.OrderServiceRq;
import com.bruce.erpapp.service.common.OrderServiceUpdateRq;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.expression.Operation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    /**
     * 查維修單紀錄.
     *
     * @param viewForm
     * @return
     * @throws ParseException
     */
    @PostMapping("/query")
    public OrderDoQueryRs query(@RequestBody OrderDoQueryRq viewForm) throws ParseException {
        var response = new OrderDoQueryRs();
        var rq = new OrderServiceQueryRq();
        rq.setEndDate(StringUtils.isEmpty(viewForm.getEndDate()) ? null : DateUtils.parseDate(viewForm.getEndDate(), "yyyy/MM/dd"));
        rq.setStartDate(StringUtils.isEmpty(viewForm.getStartDate()) ? null : DateUtils.parseDate(viewForm.getStartDate(), "yyyy/MM/dd"));
        rq.setCustName(viewForm.getCustName());
        rq.setOrderId(viewForm.getOrderId());
        rq.setPhone(viewForm.getPhone());
        rq.setStatus(OrderFixStatus.findByCode(viewForm.getStatus()));
        var rs = orderService.queryOrders(rq);
        response.setOrderBeanList(rs.getOrderBeanList());
        return response;
    }

    /**
     * 查維修單紀錄.
     *
     * @param orderKxy
     * @return
     */
    @GetMapping("/query/{orderKey}")
    public OrderDoQueryOneRs queryOneItem(@Param("orderKey") String orderKxy) {
        var response = new OrderDoQueryOneRs();
        var rq = new OrderServiceQueryRq();
        rq.setOrderId(orderKxy);
        var rs = orderService.queryOrder(rq);
        response.setOrder(rs.getOrderBeanList().get(0));
        return response;
    }

    /**
     * 新增維修單.
     *
     * @param viewForm
     * @return
     */
    @PostMapping("/add")
    public OrderDoSaveRs addOne(@RequestBody OrderDoSaveRq viewForm) {
        var response = new OrderDoSaveRs();
        var rq = new OrderServiceRq();
        rq.setPhone(viewForm.getPhone());
        rq.setAbout(viewForm.getMemo());
        rq.setAmount(ConvertPlusUtils.str2BigDecimal(viewForm.getFixAmount()));
        rq.setDevice(viewForm.getDevice());
        rq.setDeviceColor(viewForm.getColor());
        rq.setGender(viewForm.getGender());
        rq.setImei(viewForm.getImei());
        rq.setCustName(viewForm.getCustName());
        rq.setMaintain(viewForm.getMaintain());
        rq.setPin(viewForm.getPin());
        var rs = orderService.saveOrder(rq);
        response.setOrderId(rs.getOrderId());
        response.setDate(rs.getDate());
        response.setTime(rs.getTime());
        return response;
    }

    /**
     * 更新維修單.
     *
     * @param viewForm
     * @return
     */
    @PostMapping("/update")
    public OrderDoUpdateRs update(@RequestBody OrderDoUpdateRq viewForm) {
        var response = new OrderDoUpdateRs();
        var rq = new OrderServiceUpdateRq();
        rq.setPhone(viewForm.getPhone());
        rq.setOrderId(viewForm.getOrderId());
//        rq.setMemo(viewForm.getMemo());
        rq.setAmount(ConvertPlusUtils.str2BigDecimal(viewForm.getFixAmount()));
        rq.setDevice(viewForm.getDevice());
        rq.setDeviceColor(viewForm.getColor());
        rq.setGender(viewForm.getGender());
        rq.setCustName(viewForm.getCustName());
        rq.setErrorDesc(viewForm.getErrorDesc());
        rq.setPin(viewForm.getDevicePin());
        rq.setStatus(viewForm.getStatusCode());
        orderService.updateOrder(rq);
        return response;
    }

}
