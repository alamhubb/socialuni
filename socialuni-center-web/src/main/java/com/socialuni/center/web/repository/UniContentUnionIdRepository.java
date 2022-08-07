package com.socialuni.center.web.repository;


import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniContentUnionIdRepository extends JpaRepository<UniContentUnionIdDO, Integer> {

//    UniContentUnionIdDO findByOriginalDevIdAndOriginalContentUnionId(Integer originalDevId, Integer originalContentUnionId);

//    UniContentUnionIdDO findByOriginalDevIdAndOriginalContentUnionId(Integer originalDevId, Integer originalContentUnionId);


    UniContentUnionIdDO findByContentTypeAndContentId(String contentType, Integer contentId);
    UniContentUnionIdDO findByUnionId(String unionId);

    UniContentUnionIdDO findOneById(Integer id);

}

