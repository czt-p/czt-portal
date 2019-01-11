package com.zjcds.czt.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luokp on 2019/1/2.
 */
public class EnumValueConverter<T extends EnumValue<K, V>, K, V> implements AttributeConverter<EnumValue<K, V>, K> {

    private Map<K, T> enumMap = new HashMap<>();

    public EnumValueConverter(Class<T> enumClass) {
        T[] enums = enumClass.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getKey(), e);
        }
    }

    @Override
    public K convertToDatabaseColumn(EnumValue<K, V> attribute) {
        return attribute == null ? null : attribute.getKey();
    }

    @Override
    public EnumValue<K, V> convertToEntityAttribute(K dbData) {
        if (dbData == null) {
            return null;
        }
        T result = enumMap.get(dbData);
//        if (result == null) {
//            throw new IllegalArgumentException("No element matches " + dbData);
//        }
        return result;
    }
}
