package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.api.dao.DO.ErrorRequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRequestLogRepository extends JpaRepository<ErrorRequestLogDO, Long> {
}

