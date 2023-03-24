package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class SocialTagDOFactory implements Serializable {
    public static TagDO toTagDO(String tagName, String tagDesc, Integer mineUserId) {
        TagDO TagDO = new TagDO();
        TagDO.setApplyUserId(mineUserId);
        //先默认为1，以后设置可以更改选择类型
        TagDO.setTagTypeId(1);
        TagDO.setName(tagName);
        TagDO.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + "qingchi/static/qclogo.jpg!avatar");
        TagDO.setDescription(tagDesc);
        TagDO.setStatus(ContentStatus.init);
        TagDO.setCount(0);
        TagDO.setTalkCount(0);
        TagDO.setShowFront(true);
        Date curDate = new Date();
        TagDO.setCreateTime(curDate);
        TagDO.setUpdateTime(curDate);
        TagDO.setVisibleGender(GenderType.all);
        return TagDO;
    }
}
