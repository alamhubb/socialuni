package com.socialuni.social.report.sdk.dao.repository;


import com.socialuni.social.report.sdk.dao.DO.IllegalWordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IllegalWordRepository extends JpaRepository<IllegalWordDO, Integer> {
    List<IllegalWordDO> findAllByStatus(String status);
}

