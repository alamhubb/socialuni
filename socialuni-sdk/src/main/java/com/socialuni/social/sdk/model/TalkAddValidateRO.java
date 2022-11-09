package com.socialuni.social.sdk.model;

import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.sdk.dao.DO.DistrictDO;
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
    private SocialuniCircleDO circle;

    public TalkAddValidateRO(DistrictDO district, List<TagDO> tags, SocialuniCircleDO circle) {
        this.district = district;
        this.tags = tags;
        this.circle = circle;
    }
}
