package com.kimalmroth.restapidemo;

import com.kimalmroth.restapidemo.config.NotionConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(NotionConfigProperties.class)
public class RestapiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiDemoApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Home Page";
    }
}
