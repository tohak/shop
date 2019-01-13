package com.shop.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    // добовляем ресурсы
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")   //путь на сайте
                .addResourceLocations("classpath:/img/"); //путь хранения файла
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    //  бин для работы с рест запросами
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}