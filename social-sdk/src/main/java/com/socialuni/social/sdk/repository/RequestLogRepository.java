package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.RequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogDO, Long> {
}

