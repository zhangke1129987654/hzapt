package com.hzapt.provider.config;

import cn.hutool.core.util.ArrayUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class TaskAspect {

    @Autowired
    private SystemProperties systemProperties;

    @Pointcut("@annotation(com.hzapt.provider.config.Task)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Class<?> classTarget = joinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(methodName, par);
        Task task = method.getAnnotation(Task.class);
        String[] actives = task.actives();
        if (ArrayUtil.contains(actives, systemProperties.getProfilesActive())) {
            return joinPoint.proceed();
        }
        return null;
    }

}
