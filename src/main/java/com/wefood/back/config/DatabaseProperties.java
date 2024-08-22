package com.wefood.back.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "database.mysql")
@Getter
@Setter
public class DatabaseProperties {
    private String url;
    private String userName;
    private String password;
    private Integer initialSize;
    private Integer maxTotal;
    private Integer minIdle;
    private Integer maxIdle;
    private Integer maxWait;
}
