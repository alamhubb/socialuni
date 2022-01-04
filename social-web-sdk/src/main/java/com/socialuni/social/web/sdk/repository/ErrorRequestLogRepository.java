package com.socialuni.social.web.sdk.repository;


import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRequestLogRepository extends JpaRepository<ErrorRequestLogDO, Long> {
}

