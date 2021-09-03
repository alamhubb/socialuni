package com.socialuni.admin.web.repository;


import com.socialuni.social.entity.model.DO.user.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Integer> {


}

