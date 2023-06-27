package com.hzapt.provider.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.hzapt.provider.dao")
public class MybatisPlusConfig {

    /**
     * * mybatis-plus分页插件
     * <p>
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);
        innerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }

    /**
     * PageHelper分页插件
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        // 该参数默认为false -->
        // 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用 -->
        // 和startPage中的pageNum效果一样
        p.setProperty("offsetAsPageNum", "true");
        // 该参数默认为false -->
        // 设置为true时，使用RowBounds分页会进行count查询 -->
        p.setProperty("rowBoundsWithCount", "true");
        // 3.3.0版本可用 - 分页参数合理化，默认false禁用
        // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        // 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        p.setProperty("reasonable", "false");
        p.setProperty("dialect", "mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
