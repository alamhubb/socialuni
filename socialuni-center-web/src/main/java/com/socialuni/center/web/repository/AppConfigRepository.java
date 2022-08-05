package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.AppConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppConfigRepository extends JpaRepository<AppConfigDO, Integer> {
    List<AppConfigDO> findAllByStatusOrderByCreateTimeDesc(String status);
}

