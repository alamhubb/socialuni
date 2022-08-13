package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<UserDO, Integer> {

    UserDO findByName(String name);
}
