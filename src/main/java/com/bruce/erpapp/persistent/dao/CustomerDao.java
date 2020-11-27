package com.bruce.erpapp.persistent.dao;

import com.bruce.erpapp.persistent.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, Long> {

    /**
     * 利用username查找使用者.
     */
    List<CustomerEntity> findByCustomerNameLike(String customerName);

    /**
     * 利用username和電話查找使用者.
     */
    List<CustomerEntity> findByCustomerNameAndPhone(String customerName, String phone);

    /**
     * 利用userid查找使用者.
     */
    List<CustomerEntity> findByCustomerIdIn(Set<Long> customerIdList);
}
