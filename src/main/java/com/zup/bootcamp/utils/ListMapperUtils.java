package com.zup.bootcamp.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Log4j2
public class ListMapperUtils<T> {

    public void copyList(Object obj, List<T> list2, Class<T> classObj) {
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list2))) {
            List<Object> list1 = (List) obj;
            list1.forEach(item -> {
                try {
                    T data = classObj.newInstance();
                    BeanUtils.copyProperties(item, data);
                    list2.add(data);
                } catch (InstantiationException | IllegalAccessException e) {
                    log.error(e.getMessage());
                }
            });
        }
    }
}
