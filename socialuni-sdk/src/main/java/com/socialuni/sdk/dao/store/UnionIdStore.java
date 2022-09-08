package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.model.DO.UnionIdDO;
import com.socialuni.sdk.dao.repository.UnionIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Component
@Slf4j
public class UnionIdStore {
    @Resource
    UnionIdRepository unionIdRepository;

    @Async
    public void saveAsync(UnionIdDO unionIdDO) {
        unionIdRepository.save(unionIdDO);
    }
}