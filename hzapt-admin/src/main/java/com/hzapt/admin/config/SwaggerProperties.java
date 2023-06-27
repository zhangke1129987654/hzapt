package com.hzapt.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义swagger配置类
 *
 * @author LIJIAN
 */
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    private boolean enable;

    /**
     * 项目应用名
     */
    private String title;

    /**
     * 项目版本信息
     */
    private String version;

    /**
     * 项目描述信息
     */
    private String description;



}
