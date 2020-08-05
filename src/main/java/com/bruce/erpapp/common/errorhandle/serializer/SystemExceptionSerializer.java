package com.bruce.erpapp.common.errorhandle.serializer;

import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SystemExceptionSerializer extends JsonSerializer<SystemException> {
    @Override
    public void serialize(SystemException value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();
        
        // 回應狀態
        jsonGenerator.writeObjectField("status", value.getStatus());

        jsonGenerator.writeEndObject();
    }
}
