package com.hzapt.common.domain.annotation;

import java.lang.annotation.*;

/**
 * 菜单权限注解
 *
 * @author LJ
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MenuPower {

    String value();

}
