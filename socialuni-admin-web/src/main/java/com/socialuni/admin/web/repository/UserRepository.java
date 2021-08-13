package com.socialuni.admin.web.repository;


import com.socialuni.social.entity.model.DO.user.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDO, Integer> {


}

