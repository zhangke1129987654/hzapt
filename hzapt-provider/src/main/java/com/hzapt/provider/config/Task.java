package com.hzapt.provider.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于指定环境下运行任务
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Task {

    /**
     * 环境配置：dev,gaprod,zwprod
     *
     *
     * @return
     */
    String[] actives();
}
