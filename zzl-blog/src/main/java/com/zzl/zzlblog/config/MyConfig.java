package com.zzl.zzlblog.config;

import com.zzl.zzlblog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyConfig {

    @Value("${file.upload.path}")
    String fileUploadRootPath;

    @Bean
    public String fileUploadRootPath(){
        return fileUploadRootPath;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/accounts/**")
                        .addResourceLocations("file:"+fileUploadRootPath);
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/contact").setViewName("contact");
                registry.addViewController("/about").setViewName("about");
                registry.addViewController("/album").setViewName("album");
                registry.addViewController("/blog").setViewName("blog");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/space/**");
            }
        };
    }
}
