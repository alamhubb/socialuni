package com.socialuni.social.community.sdk.model;

import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import com.socialuni.social.tag.dao.DO.SocialuniTagDO;

import com.socialuni.social.user.sdk.dao.DO.SocialuniDistrictDO;
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
    private SocialuniDistrictDO district;
    private List<SocialuniTagDO> tags;
    private SocialuniCircleDO circle;

    public TalkAddValidateRO(SocialuniDistrictDO district, List<SocialuniTagDO> tags, SocialuniCircleDO circle) {
        this.district = district;
        this.tags = tags;
        this.circle = circle;
    }
}
