package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;


@SpringBootApplication
@Import(CorsConfig.class)
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class SkeFbBookShopApplication {


    public static void main(String[] args) {
        SpringApplication.run(SkeFbBookShopApplication.class, args);
    }
}
