package com.socialuni.sdk.dao.repository.log;

import com.socialuni.sdk.dao.DO.UserLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLogDO, Long> {

}

