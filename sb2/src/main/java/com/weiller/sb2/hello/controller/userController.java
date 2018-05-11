package com.weiller.sb2.hello.controller;

import com.weiller.sb2.hello.service.UserService;
import com.weiller.sb2.hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public Mono<User> create(@RequestBody User   user ) {
        return this.userService.createOrUpdate(user);
    }

    @GetMapping("/get")
    public Mono<User> get( Integer id ) {
        return this.userService.getById(id);
    }

    @GetMapping("/del")
    public Mono<Tuple2> delete(Integer id){
        Tuple2<Boolean, String> tuple2 = Tuples.of(true, "删除成功！");
        return Mono.just(tuple2);
    }


}
