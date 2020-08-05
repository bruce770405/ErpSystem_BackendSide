package com.bruce.erpapp.common.errorhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS("0000", "success");

    private String code;

    private String message;

    private final String systemId = "ERP";

}
