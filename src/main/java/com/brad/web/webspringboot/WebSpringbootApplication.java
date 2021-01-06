package com.brad.web.webspringboot;

import com.brad.web.webspringboot.common.annotation.FebsShiro;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

@FebsShiro
public class WebSpringbootApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebSpringbootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }


}
