package com.socialuni.sdk.model;

import com.socialuni.sdk.model.DO.DistrictDO;
import com.socialuni.sdk.model.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.DO.tag.TagDO;
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
