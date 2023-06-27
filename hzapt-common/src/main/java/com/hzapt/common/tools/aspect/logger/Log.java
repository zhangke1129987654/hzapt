package com.hzapt.common.tools.aspect.logger;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作类型（1-新增 2-修改 3-删除）
     */
    public int type();

}