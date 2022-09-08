package com.socialuni.sdk.dao.repository;


import com.socialuni.sdk.dao.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

