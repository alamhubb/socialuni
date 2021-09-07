package com.qingchi.web.repsotiory;


import com.qingchi.web.model.DO.user.IdCardDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdCardRepository extends JpaRepository<IdCardDO, Integer> {
    List<IdCardDO> findByUserIdAndStatus(Integer userId, String status);
}

