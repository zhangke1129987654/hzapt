package com.hzapt.admin.controller;

import com.hzapt.client.domain.vo.UserVo;
import com.hzapt.client.logic.UserLogic;
import com.hzapt.common.domain.constant.CommonConstant;
import com.hzapt.common.domain.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Controller 表明该类是控制器
 * SpringbootFreemarkerApplication类启动时会默认扫描
 * 但是要在 com.zhou.sbfreemarker 之下
 */
@Validated
@RestController
public class LoginController {

    @DubboReference
    private UserLogic userLogic;

    /**
     * Get请求才能访问
     * 对应 localhost/login
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        // View 视图
        // SB会自动在 resources/templates 中去查找 login.ftl
        // resources/static 是放静态文件的地方
        mav.setViewName("login");
        // Model数据
//        mav.addObject("hello", "Hello Freemarkder!");
//        mav.addObject("name", "张三");
//        mav.addObject("age", "20");
        return mav;
    }

    @PostMapping("/saveForm")
    public ResultVo<String> saveForm(@RequestBody UserVo userVo) {
        userLogic.setUser(userVo);
        return ResultVo.success("成功");
    }
    /**
     * Get请求才能访问
     * 对应 localhost/login
     * @return
     */
    @GetMapping("/introduce")
    public ModelAndView introduce(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ct");
        return mav;
    }
}

