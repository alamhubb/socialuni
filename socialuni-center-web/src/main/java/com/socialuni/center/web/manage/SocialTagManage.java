package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.DO.tag.TagDO;
import com.socialuni.center.web.model.QO.community.tag.TagAddQO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.factory.community.SocialTagDOFactory;
import com.socialuni.center.web.repository.community.TagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagRepository;

    public TagDO createTagDO(TagAddQO tagAddQO, Integer mineUserId) {
        TagDO tagDO = SocialTagDOFactory.toTagDO(tagAddQO.getTagName(), tagAddQO.getDescription(), mineUserId);
        tagDO = tagRepository.save(tagDO);
        return tagDO;
    }

    public TagDO createDevAccountTagDO(Integer devId, String appName) {
        TagDO tagDO = SocialTagDOFactory.toTagDO(appName, "开发者对应的话题", SocialAppConfig.getSystemUserId());

        tagDO.setTagTypeId(32);
        tagDO.setDevId(devId);
        tagDO.setShowFront(false);

        tagDO = tagRepository.save(tagDO);
        return tagDO;
    }
}