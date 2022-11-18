package com.socialuni.social.common.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 14:10
 */
public class JsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parse(String json, Class<T> beanClass) {
        try {
            return JsonUtil.objectMapper.readValue(json, beanClass);
        } catch (JsonProcessingException e) {
            throw new SocialSystemException("解析json错误");
        }
    }

    public static <T> T parse(String json, T t) {
        try {
            Class<T> beanClass = (Class<T>) t.getClass();
            return JsonUtil.objectMapper.readValue(json, beanClass);
        } catch (JsonProcessingException e) {
            throw new SocialSystemException("解析json错误");
        }
    }

    public static <T> T parse(ByteBuffer byteBufferJson, Class<T> beanClass) {
        ObjectReader reader = JsonUtil.objectMapper.readerFor(beanClass);
        try {
            return reader.readValue(byteBufferJson.array());
        } catch (IOException e) {
            throw new SocialSystemException("解析byteBufferJson错误");
        }
    }

    public static <T> T parse(ByteBuffer byteBufferJson, T t) {
        ObjectReader reader = JsonUtil.objectMapper.readerFor(t.getClass());
        try {
            return reader.readValue(byteBufferJson.array());
        } catch (IOException e) {
            throw new SocialSystemException("解析byteBufferJson错误");
        }
    }
}
