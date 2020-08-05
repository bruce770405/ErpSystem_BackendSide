package com.bruce.erpapp.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

@Log4j2
public final class JsonUtils {

    private static ObjectMapper objectMapper;


    static {
        registerObjectMapper();
    }

    private JsonUtils() {

    }

    /**
     * 設定JSON與JAVA物件的轉換規則.
     */
    private static void registerObjectMapper() {
        objectMapper = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE).build();
    }

    /**
     * 物件轉換為Json字串.
     *
     * @param object 轉換的物件.
     * @return String
     */
    public static String getJson(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Json字串轉換為物件.
     * <p>
     * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
     * </p>
     *
     * @param <T>   T.
     * @param json  json字串.
     * @param clazz 欲轉換的Java Class.
     * @return T 轉換後的物件
     */
    public static <T> T getObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue((String) json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
