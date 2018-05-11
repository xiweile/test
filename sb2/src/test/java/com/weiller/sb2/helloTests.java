package com.weiller.sb2;

import com.weiller.sb2.hello.entity.Person;
import com.weiller.sb2.hello.entity.User;
import com.weiller.sb2.hello.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableConfigurationProperties
public class helloTests {

    @Autowired
    User user;

    @Autowired
    Person person;

    @Test
    public void userTest_1(){
        try {
            System.out.println(user.toString());
            System.out.println(person.toString());
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

}
