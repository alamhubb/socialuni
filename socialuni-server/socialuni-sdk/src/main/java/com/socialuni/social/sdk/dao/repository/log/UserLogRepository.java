package com.socialuni.social.sdk.dao.repository.log;

import com.socialuni.social.sdk.dao.DO.UserLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLogDO, Long> {

}

