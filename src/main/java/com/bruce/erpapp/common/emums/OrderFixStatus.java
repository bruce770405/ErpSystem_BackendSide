package com.bruce.erpapp.common.emums;

import lombok.Getter;

import java.util.Arrays;

public enum OrderFixStatus {

    FIXING("1", "維修中"),
    FIXED("2", "維修完成"),
    FINISH("0", "取件"),
    IN_ORDER("8", "收件"),
    REJECT("-1", "報價不修"),
    UNKNOWN("9", "未知");

    @Getter
    private final String code;

    @Getter
    private final String memo;


    OrderFixStatus(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static OrderFixStatus findByCode(String code) {
        return Arrays.stream(OrderFixStatus.values()).filter(type -> type.getCode().equals(code)).findFirst().orElse(UNKNOWN);
    }
}
