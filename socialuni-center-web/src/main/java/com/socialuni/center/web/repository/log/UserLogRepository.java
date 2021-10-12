package com.socialuni.center.web.repository.log;

import com.socialuni.center.web.model.DO.UserLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLogDO, Long> {

}

