package com.hzapt.admin.config;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.List;

/***
 * Swagger配置
 *
 * @author LIJIAN
 *
 */
@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket devopsApi() {
        return createRestApi("接口信息", "com.hzapt.admin.controller");
    }


    private Docket createRestApi(String groupName, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
                // 分组名称
                .groupName(groupName)
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(swaggerProperties.isEnable())
                // 将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(new LinkedHashSet<>(CollUtil.toList("https", "http")));
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置文档的标题
                .title(swaggerProperties.getTitle())
                // 设置文档的描述
                .description(swaggerProperties.getDescription())
                // 设置文档的版本信息
                .version(swaggerProperties.getVersion())
                // 设置更新服务条款url
               .termsOfServiceUrl("http://127.0.0.1:8050/")
                .build();
    }

    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils
                    .getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration.excludePathPatterns("/")
                            .excludePathPatterns("/swagger**/**")
                            .excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**")
                            .excludePathPatterns("/doc.html")
                            .excludePathPatterns("/error")
                            .excludePathPatterns("/csrf");
                }
            }
        } catch (Exception e) {
            log.error("Swagger文档异常：{}", e);
        }
    }

}
