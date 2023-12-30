package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogDO, Long> {
}

