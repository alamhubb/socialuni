package com.socialuni.social.sdk.model;

import com.socialuni.entity.model.DO.DistrictDO;
import com.socialuni.entity.model.DO.tag.TagDO;
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

    public TalkAddValidateRO(DistrictDO district, List<TagDO> tags) {
        this.district = district;
        this.tags = tags;
    }
}
