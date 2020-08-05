package com.bruce.erpapp.common.errorhandle;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
         return EqualsBuilder.reflectionEquals(this, obj);
//        if (obj == this) {
//            return true;
//        }
//        if (null == obj) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//
//            return false;
//        }
//
//        ErrorStatus status = (ErrorStatus)obj;
//
//        String errorCode1 = getPureErrorCode(errorCode);
//
//        String errorCode2 = getPureErrorCode(status.getErrorCode());
//
//        return StringUtilsEx.equals(systemId, status.getSystemId()) && StringUtilsEx.equals(errorCode1, errorCode2);
    }

}
