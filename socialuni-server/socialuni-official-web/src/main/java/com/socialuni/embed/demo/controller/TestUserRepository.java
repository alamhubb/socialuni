package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<UserDO, Long> {

    UserDO findByName(String name);

    UserDO findOneById(Long id);
}
