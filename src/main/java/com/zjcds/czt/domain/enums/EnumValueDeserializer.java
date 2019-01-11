package com.zjcds.czt.domain.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luokp on 2019/1/2.
 */
public abstract class EnumValueDeserializer<E extends EnumValue> extends JsonDeserializer<E> {

    private Map<String, E> enumMap = new HashMap<>();

    public EnumValueDeserializer(Class<E> enumType) {
        E[] enums = enumType.getEnumConstants();
        for (E e : enums) {
            enumMap.put(e.getKey().toString(), e);
        }
    }

    @Override
    public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String key = p.getText();
        E result = enumMap.get(key);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + key);
        }
        return result;
    }
}
