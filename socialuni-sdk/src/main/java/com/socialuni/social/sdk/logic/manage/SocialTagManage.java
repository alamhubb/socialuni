package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.logic.factory.community.SocialTagDOFactory;
import com.socialuni.social.community.sdk.api.TagInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagInterface tagApi;

    public TagModel createTagDO(TagAddQO tagAddQO, Integer mineUserId) {
        TagModel tagModel = SocialTagDOFactory.toTagDO(tagAddQO.getTagName(), tagAddQO.getDescription(), mineUserId);
        tagModel = tagApi.savePut(tagModel);
        return tagModel;
    }

    public TagModel createDevAccountTagDO(Integer devId, String appName) {
        TagModel tagModel = SocialTagDOFactory.toTagDO(appName, "开发者对应的话题", SocialuniSystemConst.getSystemUserId());

        tagModel.setTagTypeId(32);
        tagModel.setDevId(devId);
        tagModel.setShowFront(false);

        tagModel = tagApi.savePut(tagModel);
        return tagModel;
    }
}
