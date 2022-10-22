package com.socialuni.social.sdk.logic.factory.community;

import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class SocialTagDOFactory implements Serializable {
    public static TagModel toTagDO(String tagName, String tagDesc, Integer mineUserId) {
        TagModel tagModel = new TagModel();
        tagModel.setApplyUserId(mineUserId);
        //先默认为1，以后设置可以更改选择类型
        tagModel.setTagTypeId(1);
        tagModel.setName(tagName);
        tagModel.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + "qingchi/static/qclogo.jpg!avatar");
        tagModel.setDescription(tagDesc);
        tagModel.setStatus(ContentStatus.enable);
        tagModel.setCount(0);
        tagModel.setTalkCount(0);
        tagModel.setShowFront(true);
        Date curDate = new Date();
        tagModel.setCreateTime(curDate);
        tagModel.setUpdateTime(curDate);
        tagModel.setVisibleGender(GenderType.all);
        return tagModel;
    }
}
