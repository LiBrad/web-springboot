package com.brad.web.webspringboot.common.runner;

import com.brad.web.webspringboot.common.entity.FebsConstant;
import com.brad.web.webspringboot.common.properties.FebsProperties;
import com.brad.web.webspringboot.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author MrBird
 * @author FiseTch
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FebsStartedUpRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final FebsProperties febsProperties;

    @Value("${server.port:8080}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;
    @Value("${spring.application.package-time:'1970-01-01T00:00:00Z'}")
    private String packageTime;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = febsProperties.getShiro().getLoginUrl();
            if (StringUtils.isNotBlank(contextPath)) {
                url += contextPath;
            }
            if (StringUtils.isNotBlank(loginUrl)) {
                url += loginUrl;
            }
            log.info("  ____                _  __          __  _        _____           _\n ");
            log.info("|  _ \\              | | \\ \\        / / | |      / ____|         | |");
            log.info("| |_) |_ __ __ _  __| |  \\ \\  /\\  / /__| |__   | (___  _   _ ___| |_ ___ _ __ ___\n");
            log.info("|  _ <| '__/ _` |/ _` |   \\ \\/  \\/ / _ \\ '_ \\   \\___ \\| | | / __| __/ _ \\ '_ ` _ \\");
            log.info("| |_) | | | (_| | (_| |    \\  /\\  /  __/ |_) |  ____) | |_| \\__ \\ ||  __/ | | | | |");
            log.info("|____/|_|  \\__,_|\\__,_|     \\/  \\/ \\___|_.__/  |_____/ \\__, |___/\\__\\___|_| |_| |_|");
            log.info("                                                        __/ |");
            log.info("                                                       |___/");
            log.info("                                                      ");
            log.info("BradWebSystem网站系统启动完毕，系统编译打包时间：{}，地址：{}", DateUtil.formatUtcTime(packageTime), url);

            boolean auto = febsProperties.isAutoOpenBrowser();
            if (auto && StringUtils.equalsIgnoreCase(active, FebsConstant.DEVELOP)) {
                String os = System.getProperty("os.name");
                // 默认为 windows时才自动打开页面
                if (StringUtils.containsIgnoreCase(os, FebsConstant.SYSTEM_WINDOWS)) {
                    //使用默认浏览器打开系统登录页
                    Runtime.getRuntime().exec("cmd  /c  start " + url);
                }
            }
        }
    }
}
