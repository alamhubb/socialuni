package com.socialuni.social.sdk.model;

import com.socialuni.social.entity.model.DO.DistrictDO;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-30 14:08
 */
@Data
@NoArgsConstructor
public class TalkAddValidateRO {
    private DistrictDO district;
    private List<TagDO> tags;
    private SocialCircleDO circle;

    public TalkAddValidateRO(DistrictDO district, List<TagDO> tags, SocialCircleDO circle) {
        this.district = district;
        this.tags = tags;
        this.circle = circle;
    }
}
