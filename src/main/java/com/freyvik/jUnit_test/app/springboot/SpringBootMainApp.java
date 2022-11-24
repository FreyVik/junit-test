package com.freyvik.jUnit_test.app.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringBootMainApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainApp.class, args);
    }
}
