package com.bruce.erpapp.common.emums;

import lombok.Getter;

import java.util.Arrays;


public enum GenderType {

    MALE("male", 1),

    FEMALE("female", 0),

    UNKNOWN("unknown", -1);

    @Getter
    private String code;

    @Getter
    private int dbCode;

    private GenderType(String code, int dbCode) {
        this.code = code;
        this.dbCode = dbCode;
    }

    public static GenderType findByCode(String code) {
        return Arrays.asList(GenderType.values()).stream().filter(type -> type.getCode().equals(code)).findFirst().orElseGet(() -> UNKNOWN);
    }

    public static GenderType findByDbCode(int code) {
        return Arrays.asList(GenderType.values()).stream().filter(type -> type.getDbCode() == Integer.valueOf(code)).findFirst().orElseGet(() -> UNKNOWN);
    }

}
