package com.weiller.sb2.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @ResponseBody
    @RequestMapping("world")
    public Object helloWorld(){
        return "Hello World";
    }

    @RequestMapping("page")
    public String page(){

        return "sse";
    }

}
