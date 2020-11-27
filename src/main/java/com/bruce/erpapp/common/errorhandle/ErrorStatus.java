package com.bruce.erpapp.common.errorhandle;

import java.io.Serializable;

public class ErrorStatus implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * error code 和 交易代號的分隔符號
     */
    public final static String ERROR_DELIM = ":";


    /**
     * 系統代碼
     */
    protected String systemId = "ERP";

    /**
     * 交易回應代碼
     */
    protected String errorCode;


    /**
     * 狀態描述
     */
    protected String errorDesc;

    public ErrorStatus() {
        super();
    }

    public ErrorStatus(String systemId, String errorCode, String errorDesc) {
        this.systemId = systemId;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }


    public ErrorStatus(ErrorCode code) {
        this.systemId = code.getSystemId();
        this.errorCode = code.getCode();
        this.errorDesc = code.getMessage();
    }

}
