package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.UniThirdContentDO;
import com.socialuni.center.web.model.DO.UniUserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniThirdContentRepository extends JpaRepository<UniThirdContentDO, Integer> {

    UniThirdContentDO findByDevIdAndContentTypeAndThirdId(Integer devId, String contentType, String thirdId);

}

