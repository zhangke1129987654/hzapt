package com.hzapt.common.mapstruct;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MapStruct如何String与Date互转
 *
 * @author LIJIAN
 */
@Component
public class ListStrMapper {

    public String asString(List<String> list) {
        return CollUtil.join(list,",");
    }

    public List<String> asList(String listStr) {
        if (StrUtil.isNotBlank(listStr)) {
            return Arrays.stream(listStr.split(",")).collect(Collectors.toList());
        }
        return CollUtil.toList();
    }

}
