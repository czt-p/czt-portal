package com.zjcds.czt.service;

import com.zjcds.czt.domain.enums.EnumValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luokp on 2019/1/11.
 */
@Service
public class EnumValueService {

    public <E extends EnumValue> List<Map<Object, Object>> toList(Class<E> enumClass) {
        E[] enums = enumClass.getEnumConstants();
        List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>(enums.length);
        for (E e : enums) {
            Map<Object, Object> map = new HashMap<Object, Object>();
            EnumValue enumValue = (EnumValue) e;
            map.put("key", enumValue.getKey());
            map.put("value", enumValue.getValue());
            result.add(map);
        }
        return result;
    }

}
