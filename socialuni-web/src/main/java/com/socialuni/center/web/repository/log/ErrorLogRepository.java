package com.socialuni.center.web.repository.log;

import com.socialuni.center.web.model.DO.log.ErrorLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLogDO, Long> {

}
