package com.weiller.sb2.hello.configuration;

import com.weiller.sb2.hello.filter.helloFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class HelloConfiguration {

    @Bean
    public FilterRegistrationBean helloFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ServletRegistrationBean<?> bean = new ServletRegistrationBean<>();
        registrationBean.setFilter(new helloFilter());
        registrationBean.setName("helloFilter");
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
