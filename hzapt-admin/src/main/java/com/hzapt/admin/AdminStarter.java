package com.hzapt.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;



@Slf4j
@SpringBootApplication
@CrossOrigin // 允许跨越访问
@ComponentScan(basePackages = {"com.hzapt"})
@EnableScheduling // 开启定时任务功能
@EnableAspectJAutoProxy // 开启AOP代理
public class AdminStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminStarter.class, args);
        log.info("##################	hzapt-admin  SUCCESS");
    }

}

