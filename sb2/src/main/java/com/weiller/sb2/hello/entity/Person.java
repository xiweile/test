package com.weiller.sb2.hello.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@Data
@Component
@PropertySource({"classpath:config/person.properties"})
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {

    @Value("person.name")
    private String name;

    @Range(min = 1,max = 120)
    private Integer age;

    @Email
    private String email ;

    private Map<String,Object> maps;

    private List<Object> list;


}


