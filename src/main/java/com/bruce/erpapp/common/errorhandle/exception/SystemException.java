package com.bruce.erpapp.common.errorhandle.exception;

import com.bruce.erpapp.common.errorhandle.ErrorStatus;
import com.bruce.erpapp.common.errorhandle.serializer.SystemExceptionDeserializer;
import com.bruce.erpapp.common.errorhandle.serializer.SystemExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize(using = SystemExceptionSerializer.class)
@JsonDeserialize(using = SystemExceptionDeserializer.class)
public class SystemException extends Exception {

    /**
     * 回應狀態
     */
    protected ErrorStatus status;


    /**
     * 是否為Service產生的異常
     */
    private boolean serviceError = false;
}
