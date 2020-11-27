package com.bruce.erpapp.common.emums;

import lombok.Getter;

import java.util.Arrays;


public enum GenderType {

    FEMALE("female", 0),
    MALE("male", 1),
    UNKNOWN("unknown", 2);

    @Getter
    private final String code;

    @Getter
    private final int dbCode;

    GenderType(String code, int dbCode) {
        this.code = code;
        this.dbCode = dbCode;
    }

    public static GenderType findByCode(String code) {
        return Arrays.stream(GenderType.values()).filter(type -> type.getCode().equals(code)).findFirst().orElse(UNKNOWN);
    }

    public static GenderType findByDbCode(int code) {
        return Arrays.stream(GenderType.values()).filter(type -> type.getDbCode() == code).findFirst().orElse(UNKNOWN);
    }

}
