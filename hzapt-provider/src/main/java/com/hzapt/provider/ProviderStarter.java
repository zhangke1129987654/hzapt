package com.hzapt.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.hzapt"})
@EnableTransactionManagement // 开启事务支持
@EnableScheduling // 开启定时任务功能
@EnableAspectJAutoProxy // 开启AOP代理
public class ProviderStarter {

    public static void main(String[] args) {

        SpringApplication.run(ProviderStarter.class, args);
        log.info("##################	hzapt-provider  SUCCESS");
    }
}