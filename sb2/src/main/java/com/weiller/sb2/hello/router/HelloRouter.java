package com.weiller.sb2.hello.router;

import com.weiller.sb2.hello.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HelloRouter {

    @Autowired
    HelloHandler helloHandler;


    @Bean
    public RouterFunction<ServerResponse> helloRouterFunction(){
        return RouterFunctions.route(
                RequestPredicates.GET("/hello/webflux"),
                helloHandler::helloWorld);
    }
}
