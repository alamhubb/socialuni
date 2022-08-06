package com.socialuni.center.web.model.RO.community;

import com.socialuni.center.web.model.DO.SocialContentIdCO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class UniContentIdRO extends SocialContentIdCO {
    private String originalSocialuniId;
    private Integer originalContentUnionId;

    public UniContentIdRO(Integer id, String contentType, String originalSocialuniId, Integer originalContentUnionId) {
        super(id, contentType);
        this.originalSocialuniId = originalSocialuniId;
        this.originalContentUnionId = originalContentUnionId;
    }

    public UniContentIdRO(UniContentIdRO socialuniContentIdRO) {
        super(socialuniContentIdRO);
        this.originalSocialuniId = socialuniContentIdRO.getOriginalSocialuniId();
        this.originalContentUnionId = socialuniContentIdRO.getOriginalContentUnionId();
    }
}
