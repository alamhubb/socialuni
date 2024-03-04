package com.socialuni.social.common.sdk.utils;

import com.socialuni.social.common.sdk.dao.store.SocialTagRedis;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTagDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialTagStore {
    @Resource
    SocialTagRedis socialTagRedis;

    public List<?  extends SocialuniTagDO> findTagsByIds(List<Integer> ids) {
        List<SocialuniTagDO> TagDOs = new ArrayList<>();
        for (Integer id : ids) {
            TagDOs.add(socialTagRedis.findTagById(id));
        }
        return TagDOs;
    }
}
