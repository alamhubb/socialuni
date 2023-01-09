package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.ErrorRequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRequestLogRepository extends JpaRepository<ErrorRequestLogDO, Long> {
}

