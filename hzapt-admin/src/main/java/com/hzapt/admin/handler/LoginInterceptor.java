package com.hzapt.admin.handler;


import com.hzapt.common.domain.constant.CommonConstant;
import com.hzapt.common.domain.enums.ResultEnum;
import com.hzapt.common.exceptions.MyAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录校验拦截器
 *
 * @author LJ
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

//    @DubboReference
//    private JinZongLogic jinzongLogic;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader(CommonConstant.TOKEN);
//        MyAssert.notNull(token, ResultEnum.NOT_LOGIN);
//        JzUserVo userVo = jinzongLogic.getByToken(token);
//        MyAssert.notNull(userVo, ResultEnum.NOT_LOGIN);
//        return this.hasPower(request,handler,userVo);
        return true;
    }

    /**
     * 判断是否有权限
     *
     * @return
     */
//    private boolean hasPower(HttpServletRequest request, Object handler, SysUserVo userVo) {
//        if (BooleanEnum.FALSE.getValue().equals(userVo.getIsSuper()) && handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            // 获取方法上的注解
//            MenuPower perm = handlerMethod.getMethod().getAnnotation(MenuPower.class);
//            // 如果标记了注解，则判断权限
//            if (perm != null) {
//                List<String> perms = userVo.getMenuList().stream().map(SysMenuVo::getPerms).collect(Collectors.toList());
//                MyAssert.isTrue(CollUtil.contains(perms, perm.value()), ResultEnum.NOT_POWER);
//            }
//        }
//        request.getSession().setAttribute(CommonConstant.TOKEN, request.getHeader(CommonConstant.TOKEN));
//        return true;
//    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
             {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
    }

}
