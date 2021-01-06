package com.brad.web.webspringboot.common.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.brad.web.webspringboot.common.interceptor.DataPermissionInterceptor;
import com.brad.web.webspringboot.common.interceptor.DesensitizationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrBird
 */
@Configuration
@MapperScan("com.brad.web.webspringboot.*.mapper")
public class MybatisPlusConfigure {
    /**
     * 注册数据权限
     */
    @Bean
    @Order(0)
    public DataPermissionInterceptor dataPermissionInterceptor() {
        return new DataPermissionInterceptor();
    }

    /**
     * 数据脱敏
     */
    @Bean
    @Order(-1)
    public DesensitizationInterceptor desensitizationInterceptor() {
        return new DesensitizationInterceptor();
    }

    /**
     * 注册分页插件
     */
    @Bean
    @Order(-2)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }
}
