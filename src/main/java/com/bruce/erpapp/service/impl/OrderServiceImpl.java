package com.bruce.erpapp.service.impl;

import com.bruce.erpapp.common.emums.GenderType;
import com.bruce.erpapp.common.emums.OrderFixStatus;
import com.bruce.erpapp.common.utils.ConvertPlusUtils;
import com.bruce.erpapp.common.utils.JsonUtils;
import com.bruce.erpapp.persistent.dao.CustomerDao;
import com.bruce.erpapp.persistent.dao.OrderDao;
import com.bruce.erpapp.persistent.entity.CustomerEntity;
import com.bruce.erpapp.persistent.entity.OrderEntity;
import com.bruce.erpapp.service.OrderService;
import com.bruce.erpapp.service.bean.OrderBean;
import com.bruce.erpapp.service.common.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "OrderServiceImpl")
@Primary
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final CustomerDao customerDao;
    private final OrderDao orderDao;

    /**
     * 創建一筆新的orderId給前端.
     *
     * @return
     */
    @Override
    public String queryOrderId() {
        var pattern = "yyyyMMdd";
        var systemDate = new Date();
        var orderDate = DateFormatUtils.format(systemDate, pattern);
        var orderId = "0001";
        var entity = orderDao.findTopByOrderByOrderIdDesc();
        if (entity != null && DateUtils.isSameDay(systemDate, entity.getCreateTime())) {
            var dbId = Integer.parseInt(entity.getOrderId().substring(pattern.length()));
            orderId = String.format("%04d", (dbId + 1));
        }
        return orderDate.concat(orderId);
    }

    /**
     * 儲存一筆維修紀錄.
     *
     * @param rq
     * @return
     */
    @Override
    public OrderServiceRs saveOrder(OrderServiceRq rq) {
        var rs = new OrderServiceRs();
        var systemDate = new Date();
        //  先查客戶

        Long customerId = null;
        List<CustomerEntity> results = customerDao.findByCustomerNameAndPhone(rq.getCustName(), rq.getPhone());

        if (CollectionUtils.isEmpty(results)) { //先寫一筆
            var entity = new CustomerEntity();
            entity.setAddress("");
            entity.setCreateTime(systemDate);
            entity.setCustomerName(rq.getCustName());
            entity.setEmail("");
            entity.setLevel("0");
            entity.setPhone(rq.getPhone());
            entity.setGender(GenderType.findByCode(rq.getGender()).getDbCode());
            entity = customerDao.save(entity);
            customerId = entity.getCustomerId();
        } else if (results.size() != 1) {
            log.warn("customer not only one.");
//            throw new Exception();
        } else {
            customerId = results.get(0).getCustomerId();
        }

        var entity = new OrderEntity();
        entity.setCreateTime(systemDate);
        entity.setUpdateTime(systemDate);
        entity.setMemo(rq.getAbout());
        entity.setOrderId(this.queryOrderId());
        entity.setDeviceName(rq.getDevice());
        entity.setCustomerId(customerId);
        entity.setColor(rq.getDeviceColor());
        entity.setDevicePin(rq.getPin());
        entity.setErrorDesc(rq.getMaintain());
        entity.setImei(rq.getImei());
        entity.setFixAmount(rq.getAmount());
        entity.setStatus(OrderFixStatus.IN_ORDER.getCode());
        orderDao.save(entity);

        rs.setOrderId(entity.getOrderId());
        rs.setDate(DateFormatUtils.format(entity.getCreateTime(), "yyyy/MM/dd"));
        rs.setTime(DateFormatUtils.format(entity.getCreateTime(), "HH:mm"));
        return rs;
    }

    /**
     * @param rq
     * @return
     */
    @Override
    public OrderServiceQueryRs queryOrder(OrderServiceQueryRq rq) {
        OrderServiceQueryRs rs = queryOrders(rq);
        return rs;
    }

    @Override
    public OrderServiceQueryRs queryOrders(OrderServiceQueryRq rq) {
        var rs = new OrderServiceQueryRs();

        // 如果沒有輸入時間，查一年內資料
        var startDate = rq.getStartDate() == null ? Date.from(LocalDateTime.now().plusYears(-1).atZone(ZoneId.systemDefault()).toInstant()) : rq.getStartDate();
        var endDate = rq.getEndDate() == null ? new Date() : rq.getEndDate();

        List<OrderEntity> orderList = new ArrayList<>(1);
        // 查維修單id
        if (rq.getOrderId() != null) {

            Optional<OrderEntity> optional = orderDao.findById(rq.getOrderId());
            if (optional.isPresent()) {
                orderList.add(optional.get());
            }

            // 使用客戶姓名查詢
        } else if (!StringUtils.isEmpty(rq.getCustName())) {

            var customerEntityList = customerDao.findByCustomerNameLike(rq.getCustName());
            var customerIdList = customerEntityList.stream().map(customer -> customer.getCustomerId()).collect(Collectors.toList());
            orderList = orderDao.findByCustomerIdInAndCreateTimeBetween(customerIdList, startDate, endDate);

            //單純查時間上所有的維修紀錄
        } else {
            orderList = orderDao.findByCreateTimeBetween(startDate, endDate);
        }

        var custIdSet = orderList.stream().map(order -> order.getCustomerId()).collect(Collectors.toSet());
        var customerEntityMap = getCustomerEntityMap(custIdSet);

        rs.setOrderBeanList(orderList.stream().map(order -> {
            var bean = OrderBean.builder().orderId(order.getOrderId())
                    .device(order.getDeviceName())
                    .fixAmount(order.getFixAmount())
                    .errorDesc(order.getErrorDesc()).color(order.getColor())
                    .memo(order.getMemo())
                    .status(OrderFixStatus.findByCode(order.getStatus()).getMemo())
                    .devicePin(order.getDevicePin())
                    .gender(GenderType.findByDbCode(customerEntityMap.get(order.getCustomerId()).getGender()).getCode())
                    .phone(customerEntityMap.get(order.getCustomerId()).getPhone())
                    .updateTime(DateFormatUtils.format(order.getUpdateTime(),"yyyy/MM/dd HH:mm:ss"))
                    .custName(customerEntityMap.get(order.getCustomerId()).getCustomerName()).build();
            return bean;
        }).collect(Collectors.toList()));

        return rs;
    }

    /**
     * 由客戶id查詢整條records.
     *
     * @param custIdSet
     * @return
     */
    private Map<Long, CustomerEntity> getCustomerEntityMap(Set<Long> custIdSet) {
        List<CustomerEntity> customerEntityList = customerDao.findByCustomerIdIn(custIdSet);
        return customerEntityList.stream().collect(Collectors.toMap(e -> e.getCustomerId(), e -> e));
    }

    /**
     * memo額外設計提出增加方式.
     * @param rq
     */
    @Override
    public void updateOrder(OrderServiceUpdateRq rq) {
        var rs = new OrderServiceRs();

        orderDao.findById(rq.getOrderId()).ifPresentOrElse(
                (entity) -> {
                    entity.setFixAmount(rq.getAmount());
                    entity.setUpdateTime(new Date());
                    entity.setStatus(rq.getStatus());
                    orderDao.save(entity);
                }, () -> {
//                    throw new Exception();
                });

    }
}
