package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.sdk.dao.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

