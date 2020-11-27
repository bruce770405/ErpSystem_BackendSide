package com.bruce.erpapp.persistent.dao;

import com.bruce.erpapp.persistent.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {

    OrderEntity findTopByOrderByOrderIdDesc();

    List<OrderEntity> findByCreateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderEntity> findByCustomerIdIn(List<Long> customerIdList);

    List<OrderEntity> findByCustomerIdInAndCreateTimeBetween(List<Long> customerIdList, LocalDateTime startDate, LocalDateTime endDate);

}
