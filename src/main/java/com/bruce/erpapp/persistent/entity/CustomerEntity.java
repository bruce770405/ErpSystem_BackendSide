package com.bruce.erpapp.persistent.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER")
@Data
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "CREATE_TIME", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateTime;

    //mappedBy="CUSTOMER_ID"中的CUSTOMER_ID是Article中的CUSTOMER_ID属性
//    @OneToMany(mappedBy = "CUSTOMER_ID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderEntity> orderList;

}
