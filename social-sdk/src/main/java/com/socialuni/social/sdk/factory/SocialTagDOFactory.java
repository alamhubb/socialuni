package com.socialuni.social.sdk.factory;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class SocialTagDOFactory implements Serializable {
    public static TagDO toTagDO(String tagName, String tagDesc, Integer mineUserId) {
        TagDO tagDO = new TagDO();
        tagDO.setApplyUserId(mineUserId);
        //先默认为1，以后设置可以更改选择类型
        tagDO.setTagTypeId(1);
        tagDO.setName(tagName);
        tagDO.setAvatar(SocialAppConfig.getStaticResourceUrl() + "/qingchi/static/qclogo.jpg!avatar");
        tagDO.setDescription(tagDesc);
        tagDO.setStatus(ContentStatus.enable);
        tagDO.setCount(0);
        tagDO.setTalkCount(0);
        tagDO.setShowFront(true);
        Date curDate = new Date();
        tagDO.setCreateTime(curDate);
        tagDO.setUpdateTime(curDate);
        tagDO.setVisibleGender(GenderType.all);
        return tagDO;
    }
}
