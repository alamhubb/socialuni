package com.socialuni.social.official.logic;


import com.socialuni.social.official.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<UserDO, Integer> {

    UserDO findByName(String name);

    UserDO findOneById(Long id);
}
