package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.sdk.dao.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

