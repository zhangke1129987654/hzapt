package com.hzapt.common.mapstruct;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MapStruct如何String与Date互转
 *
 * @author LIJIAN
 */
@Component
public class SetStrMapper {

    public String asString(Set<String> list) {
        return CollUtil.join(list,",");
    }

    public Set<String> asList(String listStr) {
        if (StrUtil.isNotBlank(listStr)) {
            return Arrays.stream(listStr.split(",")).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

}
