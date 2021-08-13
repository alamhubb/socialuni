package com.socialuni.center.web.model.VO;

import com.socialuni.social.sdk.constant.GenderType;
import com.socialuni.social.sdk.constant.status.ContentStatus;
import com.socialuni.entity.model.DO.tag.TagDO;
import com.socialuni.entity.model.DO.user.UserDO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
public class TagAddVO {
    @NotBlank
    public String tagName;
    public String description;

    public TagDO toDO(UserDO user) {
        TagDO tagDO = new TagDO();
        tagDO.setApplyUserId(user.getId());
        //先默认为1，以后设置可以更改选择类型
        tagDO.setTagTypeId(1);
        tagDO.setName(tagName);
        tagDO.setAvatar("https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/static/qclogo.jpg!avatar");
        tagDO.setDescription(description);
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
