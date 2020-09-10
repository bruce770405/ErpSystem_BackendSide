package com.bruce.erpapp.persistent.entity;

import com.bruce.erpapp.common.emums.OrderFixStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "FIX_ORDER")
public class OrderEntity implements Serializable {

    @Id
    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "DEVICE_NAME", nullable = false)
    @Basic
    private String deviceName;

    @Column(name = "IMEI", nullable = false)
    @Basic
    private String imei;

    @Column(name = "COLOR")
    @Basic
    private String color;

    @Column(name = "DEVICE_PIN")
    @Basic
    private String devicePin;

    @Column(name = "FIX_AMOUNT")
    @Basic
    private BigDecimal fixAmount;

    @Column(name = "ERROR_DESC")
    @Basic
    private String errorDesc;

    @Column(name = "MEMO")
    @Basic
    private String memo;

    @Column(name = "STATUS", nullable = false)
    @Basic
    private String status = OrderFixStatus.UNKNOWN.getCode();

    @Column(name = "CREATE_TIME", nullable = false)
    @Basic
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    @Basic
    private Date updateTime;

}
