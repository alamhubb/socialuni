package com.socialuni.social.content.dao.repository;


import com.socialuni.social.content.dao.DO.IllegalWordDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IllegalWordRepository extends JpaRepository<IllegalWordDO, Integer> {
    List<IllegalWordDO> findAllByStatus(String status);
}

