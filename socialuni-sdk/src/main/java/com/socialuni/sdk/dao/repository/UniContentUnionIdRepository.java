package com.socialuni.sdk.dao.repository;


import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniContentUnionIdRepository extends JpaRepository<UniContentUnionIdDO, Integer> {
    UniContentUnionIdDO findByUuid(String unionId);

    UniContentUnionIdDO findOneById(Integer id);

}

