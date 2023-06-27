//package com.hzapt.admin.aspect;
//
//import com.hzapt.client.domain.vo.devops.system.SysLogSaveVo;
//import com.hzapt.client.domain.vo.devops.system.SysUserVo;
//import com.hzapt.client.logic.devops.system.SysLogLogic;
//import com.hzapt.client.logic.devops.system.SysUserLogic;
//import com.hzapt.common.domain.enums.ResultEnum;
//import com.hzapt.common.exceptions.MyException;
//import com.hzapt.common.tools.aspect.logger.Log;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Date;
//
//@Aspect
//@Component
//public class LogAspect {
//
//    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
//
//    @DubboReference
//    private SysLogLogic sysLogLogic;
//
//    @DubboReference
//    private SysUserLogic sysUserLogic;
//
//    //定义切点 @Pointcut
//    //在注解的位置切入代码
//    @Pointcut("@annotation( com.hzapt.common.tools.aspect.logger.Log)")
//    public void logPoinCut() {
//    }
//
//    //切面 配置通知
//    @Around("logPoinCut()")
//    public Object saveSysLog(ProceedingJoinPoint joinPoint) {
//        log.info("---------------接口日志记录---------------");
//        //从切面织入点处通过反射机制获取织入点处的方法
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        //获取切入点所在的方法
//        Method method = signature.getMethod();
//        //获取操作--方法上的SysLog的值
//        Log newLog = method.getAnnotation(Log.class);
//        // 操作人账号、姓名
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        SysLogSaveVo sysLogSaveVo = new SysLogSaveVo();
//        SysUserVo vo = sysUserLogic.getByToken(request.getHeader("token"));
//        if(vo != null) {
//            sysLogSaveVo.setPlatformId(vo.getPlatformId());//机构Id
//            sysLogSaveVo.setDepartmentId(vo.getDepartmentId());//部门Id
//            sysLogSaveVo.setUserId(vo.getId());//用户Id
//        }
//        sysLogSaveVo.setType(newLog.type());//操作类型
//        sysLogSaveVo.setIpAddr(request.getRemoteAddr());//IP地址
//        sysLogSaveVo.setHref(request.getRequestURI());//请求路径
//        sysLogSaveVo.setParams(SysLogSaveVo.getParams(joinPoint));//请求参数
//
//
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//            sysLogSaveVo.setResult(result.toString());//返回信息
//            sysLogSaveVo.setState(0);//状态 0失败1成功
//            sysLogSaveVo.setCreateTime(new Date());//操作时间
//            //调用service保存sysLog实体类到数据库
//            sysLogLogic.save(sysLogSaveVo);
//
//        } catch (Throwable e) {
//            sysLogSaveVo.setState(1);//状态 0失败1成功
//            sysLogSaveVo.setResult(e.getMessage());//返回错误信息
//            sysLogSaveVo.setCreateTime(new Date());//操作时间
//            //调用service保存sysLog实体类到数据库
//            sysLogLogic.save(sysLogSaveVo);
//
//            throw new MyException(ResultEnum.FAILURE);
//        }
//
//        return result;
//    }
//
//}