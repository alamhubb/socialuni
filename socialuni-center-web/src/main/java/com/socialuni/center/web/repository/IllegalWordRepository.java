package com.socialuni.center.web.repository;

import com.socialuni.social.entity.model.DO.keywords.IllegalWordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IllegalWordRepository extends JpaRepository<IllegalWordDO, Integer> {
    List<IllegalWordDO> findAllByStatus(String status);
}

