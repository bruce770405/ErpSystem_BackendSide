package com.bruce.erpapp.common.models;

import com.bruce.erpapp.common.errorhandle.ErrorCode;
import com.bruce.erpapp.common.errorhandle.ErrorStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData implements Serializable {

    private ErrorStatus status;

}
