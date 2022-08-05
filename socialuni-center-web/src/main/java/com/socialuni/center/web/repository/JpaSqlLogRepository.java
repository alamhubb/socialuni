package com.socialuni.center.web.repository;


import com.socialuni.social.entity.model.DO.JpaSqlLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSqlLogRepository extends JpaRepository<JpaSqlLogDO, Long> {
}

