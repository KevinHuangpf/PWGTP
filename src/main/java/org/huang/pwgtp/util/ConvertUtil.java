package org.huang.pwgtp.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConvertUtil {

    public static <T> T convert(Class<T> clazz, Object obj) {
        if(obj == null) return null;
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(obj, t);
            return t;
        } catch (Exception e) {
            log.error("ConvertUtil.convert error, target: {}, source: {}", clazz.getName(), JSON.toJSONString(obj), e);
        }
        return null;
    }

    public static <T> List<T> convertList(Class<T> clazz, List<? extends Object> list) {
        if(CollectionUtils.isEmpty(list)) return new ArrayList<>();
        return list.stream().map(o -> convert(clazz, o)).collect(Collectors.toList());
    }
}
