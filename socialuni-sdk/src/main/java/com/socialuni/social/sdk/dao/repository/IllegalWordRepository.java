package com.socialuni.social.sdk.dao.repository;

import com.socialuni.social.sdk.dao.DO.keywords.IllegalWordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IllegalWordRepository extends JpaRepository<IllegalWordDO, Integer> {
    List<IllegalWordDO> findAllByStatus(String status);
}
