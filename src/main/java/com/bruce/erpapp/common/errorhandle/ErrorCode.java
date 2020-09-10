package com.bruce.erpapp.common.errorhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS("0000", "success."),
    CREATE_ORDER_FAIL("0001", "create order fail."),
    UPDATE_ORDER_FAIL("0002", "order update fail.");

    private String code;

    private String message;

    private final String systemId = "ERP";


}
