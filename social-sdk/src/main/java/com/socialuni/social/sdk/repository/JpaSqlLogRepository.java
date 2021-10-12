package com.socialuni.social.sdk.repository;


import com.socialuni.social.entity.model.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

