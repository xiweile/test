package com.weiller.sb2.hello.controller;

import com.weiller.sb2.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/sse")
public class SseController {

    @Autowired
    UserService userService;

    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }

    @GetMapping("msg")
    public Flux<ServerSentEvent<String>> msg(){

        return Flux.interval(Duration.ofMillis(1))
                .map(seq -> createRandom())
                .map(data -> ServerSentEvent.<String>builder()
                        .event("message")
                        .id(data.toString())
                        .data("hello :"+data.toString())
                        .build());
    }

    public Integer createRandom(){
        return new Random().nextInt(100);
    }
}