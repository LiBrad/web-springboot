    package com.brad.web.webspringboot.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */
@Component
@ConfigurationProperties(prefix = "oa.nginx")
public class NginxProperties {
    private String nginxUploadPath;
    private String nginxUploadUrl;

    public String getNginxUploadPath() {
        return nginxUploadPath;
    }

    public void setNginxUploadPath(String nginxUploadPath) {
        this.nginxUploadPath = nginxUploadPath;
    }

    public String getNginxUploadUrl() {
        return nginxUploadUrl;
    }

    public void setNginxUploadUrl(String nginxUploadUrl) {
        this.nginxUploadUrl = nginxUploadUrl;
    }
}
