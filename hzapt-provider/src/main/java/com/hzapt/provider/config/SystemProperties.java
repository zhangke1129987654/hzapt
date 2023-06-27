package com.hzapt.provider.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@Component
public class SystemProperties {

    @Value("${spring.profiles.active:}")
    private String profilesActive;

    @Value("${system.file.task.dir:}")
    private String fileTaskDir;

    @Value("${system.file.temp.dir:}")
    private String fileTempDir;

    /**
     * 生成任务文件路径
     * @param fileName 企业虚开判断.xlsx
     * @return
     */
    public String getTaskFilePath(String fileName) {
        int index = fileName.lastIndexOf(".");
        String dateFormat = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        String name = fileName.substring(0, index) + "_" + dateFormat + fileName.substring(index, fileName.length());

        return fileTaskDir + "/" + DateUtil.formatDate(new Date()) + "/" + name;
    }

    /**
     * 生成临时存储文件路径
     * @param fileName 企业虚开判断.xlsx
     * @return
     */
    public String getTempFilePath(String fileName) {
        int index = fileName.lastIndexOf(".");
        String dateFormat = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        String name = fileName.substring(0, index) + "_" + dateFormat + fileName.substring(index, fileName.length());

        return fileTempDir + "/" + DateUtil.formatDate(new Date()) + "/" + name;
    }

}