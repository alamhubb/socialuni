package com.socialuni.sdk.repository.log;

import com.socialuni.sdk.model.DO.UserLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLogDO, Long> {

}

