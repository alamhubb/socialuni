package com.socialuni.social.web.sdk.repository;


import com.socialuni.social.web.sdk.model.RequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogDO, Long> {
}

