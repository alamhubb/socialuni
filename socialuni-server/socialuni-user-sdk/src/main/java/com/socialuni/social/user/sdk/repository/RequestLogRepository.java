package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.RequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogDO, Long> {
}

