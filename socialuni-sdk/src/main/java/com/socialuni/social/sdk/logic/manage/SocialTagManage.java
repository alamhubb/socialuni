package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.community.sdk.repository.TagRepository;
import com.socialuni.social.sdk.logic.factory.community.SocialTagDOFactory;
import com.socialuni.social.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagApi;

    public TagDO createTagDO(TagAddQO tagAddQO, Integer mineUserId) {
        TagDO TagDO = SocialTagDOFactory.toTagDO(tagAddQO.getTagName(), tagAddQO.getDescription(), mineUserId);
        TagDO = tagApi.savePut(TagDO);
        return TagDO;
    }

    public TagDO createDevAccountTagDO(Integer devId, String appName) {
        TagDO TagDO = SocialTagDOFactory.toTagDO(appName, "开发者对应的话题", DevAccountFacade.getDevUserId());

        TagDO.setTagTypeId(32);
        TagDO.setDevId(devId);
        TagDO.setShowFront(false);

        TagDO = tagApi.savePut(TagDO);
        return TagDO;
    }
}
