package com.androidwatcher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.androidwatcher.exception.JsonException;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();

    public static <T> String objectToJson(T t) {
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T jsonToObject(String json,Class<T> klass) {
        try {
            return OBJECT_MAPPER.readValue(json,klass);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
}
