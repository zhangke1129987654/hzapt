package com.hzapt.admin.config;

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
     * @param fileName
     * @return
     */
    public String getTaskFilePath(String fileName) {
        return fileTaskDir + "/" + DateUtil.formatDate(new Date()) + "/" + getFileName(fileName);
    }

    public String getFileName(String name) {
        int index = name.lastIndexOf(".");
        String dateFormat = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        return name.substring(0, index) + "_" + dateFormat + name.substring(index, name.length());
    }

}