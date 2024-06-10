package com.socialuni.social.community.sdk.logic.manage.talk;

import com.socialuni.social.tag.dao.DO.SocialuniTagDO;
import com.socialuni.social.tag.dao.repository.TagRepository;
import com.socialuni.social.tag.factory.SocialTagDOFactory;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagApi;

    public SocialuniTagDO createTagDO(TagAddQO tagAddQO, Integer mineUserId) {
        SocialuniTagDO TagDO = SocialTagDOFactory.toTagDO(tagAddQO.getTagName(), tagAddQO.getDescription(), mineUserId);
        TagDO = tagApi.savePut(TagDO);
        return TagDO;
    }

    public SocialuniTagDO createDevAccountTagDO(Integer devId, String appName) {
        SocialuniTagDO TagDO = SocialTagDOFactory.toTagDO(appName, "开发者对应的话题", DevAccountFacade.getDevUserId());

        TagDO.setTagTypeId(32);
        TagDO.setDevId(devId);
        TagDO.setShowFront(false);

        TagDO = tagApi.savePut(TagDO);
        return TagDO;
    }
}
