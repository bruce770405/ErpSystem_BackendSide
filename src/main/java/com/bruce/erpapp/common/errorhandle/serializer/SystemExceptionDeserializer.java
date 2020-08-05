package com.bruce.erpapp.common.errorhandle.serializer;

import com.bruce.erpapp.common.errorhandle.ErrorStatus;
import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.bruce.erpapp.common.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SystemExceptionDeserializer extends JsonDeserializer<SystemException> {

    @Override
    public SystemException deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        SystemException error = new SystemException();

        JsonNode node = jp.readValueAsTree();

        // 錯誤訊息參數
        JsonNode paramsNode = node.get("params");
        if (paramsNode != null) {

//            ErrorParams params = null;
//            try {
//                params = JsonUtilsEx.getObject(paramsNode.toString(), ErrorParams.class);
//            } catch (Exception e) {
//                log.error(e, e);
//            }
//            error.setParams(params);
        }

        // 回應狀態
        JsonNode statusNode = node.get("status");

        if (statusNode != null) {
            ErrorStatus status = null;
            try {
                status = JsonUtils.getObject(statusNode.toString(), ErrorStatus.class);
            } catch (Exception e) {
                log.error("Deserializer errorCode is fail.", e);
            }

            error.setStatus(status);
        }

        return error;
    }
}
