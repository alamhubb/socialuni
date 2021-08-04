package com.socialuni.sdk.manage;

import com.socialuni.sdk.constant.GenderType;
import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.TagRepository;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialTagManage {
    @Resource
    private TagRepository tagRepository;

    public TagDO createTagDO(UserDO mineUser, TagAddQO tagAddQO) {
        TagDO tagDO = new TagDO();
        tagDO.setApplyUserId(mineUser.getId());
        //先默认为1，以后设置可以更改选择类型
        tagDO.setTagTypeId(1);
        tagDO.setName(tagAddQO.getTagName());
        tagDO.setAvatar("https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/static/qclogo.jpg!avatar");
        tagDO.setDescription(tagAddQO.getDescription());
        tagDO.setStatus(ContentStatus.enable);
        tagDO.setCount(0);
        tagDO.setTalkCount(0);
        tagDO.setShowFront(true);
        Date curDate = new Date();
        tagDO.setCreateTime(curDate);
        tagDO.setUpdateTime(curDate);
        tagDO.setVisibleGender(GenderType.all);

        tagDO = tagRepository.save(tagDO);
        return tagDO;
    }
}
