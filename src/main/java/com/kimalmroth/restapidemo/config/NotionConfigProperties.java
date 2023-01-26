package com.kimalmroth.restapidemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties(
        String secret
) {
}