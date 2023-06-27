package com.hzapt.common.mapstruct;

import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * MapStruct如何String与Date互转
 *
 * @author LIJIAN
 */
@Component
public class DateMapper {

    public String asString(Date date) {
        return DateUtil.formatDateTime(date);
    }

    public String asString(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  date.toLocalDateTime().format(formatter);
    }

    public Date asDate(String date) {
        return DateUtil.parse(date);
    }

    public ZonedDateTime asZonedDateTime(String date) {
        String str= DateUtil.formatDateTime(DateUtil.parse(date));
        DateTimeFormatter str1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
        return ZonedDateTime.parse(str, str1);
    }

}
