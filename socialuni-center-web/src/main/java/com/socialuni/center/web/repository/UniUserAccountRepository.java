package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.UniUserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniUserAccountRepository extends JpaRepository<UniUserAccountDO, Integer> {


    UniUserAccountDO findByDevIdAndThirdUserId(Integer devId, String thirdUserId);

}

