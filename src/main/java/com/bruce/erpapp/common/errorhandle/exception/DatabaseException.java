package com.bruce.erpapp.common.errorhandle.exception;

import com.bruce.erpapp.common.errorhandle.ErrorCode;
import com.bruce.erpapp.common.errorhandle.ErrorStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DatabaseException extends Exception {

    /**
     * 回應狀態
     */
    protected ErrorStatus status;


    /**
     * 是否為Service產生的異常
     */
    private boolean serviceError = false;

    public DatabaseException(ErrorCode errorCode) {
        this.status = new ErrorStatus(errorCode);
    }
}
