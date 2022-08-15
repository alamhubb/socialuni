package com.socialuni.center.controller.upgrade;

import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import com.socialuni.sdk.repository.UniContentUnionIdRepository;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.web.sdk.utils.UUIDUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Test0815Repository {
    @Resource
    UniContentUnionIdRepository uniContentUnionIdRepository;

    @Async
    public void testUserUid() {
        UniContentUnionIdDO uniContentUnionIdDO = new UniContentUnionIdDO(ContentType.user, UUIDUtil.getUUID(), 1);
        uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
    }
}
