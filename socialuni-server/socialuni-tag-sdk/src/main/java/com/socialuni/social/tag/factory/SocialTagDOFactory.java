package com.socialuni.social.tag.factory;

import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tag.dao.DO.SocialuniTagDO;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.api.constant.GenderType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class SocialTagDOFactory implements Serializable {
    public static SocialuniTagDO toTagDO(String tagName, String tagDesc, Long mineUserId) {
        SocialuniTagDO TagDO = new SocialuniTagDO();
        TagDO.setApplyUserId(mineUserId);
        //先默认为1，以后设置可以更改选择类型
        TagDO.setTagTypeId(1);
        TagDO.setName(tagName);
        TagDO.setAvatar(SocialuniSystemConst.getTagDefaultAvatar());
        TagDO.setDescription(tagDesc);
        TagDO.setStatus(ContentStatus.enable);
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
