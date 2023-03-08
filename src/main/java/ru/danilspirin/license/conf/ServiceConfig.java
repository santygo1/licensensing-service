package ru.danilspirin.license.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "example")
public class ServiceConfig {

    private String property;

    public String getProperty(){
        return this.property;
    }

}
