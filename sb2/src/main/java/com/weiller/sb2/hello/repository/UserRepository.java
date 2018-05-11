package com.weiller.sb2.hello.repository;

import com.weiller.sb2.hello.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


}

