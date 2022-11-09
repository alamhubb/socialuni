package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

