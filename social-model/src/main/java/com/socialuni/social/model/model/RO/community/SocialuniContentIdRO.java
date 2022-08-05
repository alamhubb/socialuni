package com.socialuni.social.model.model.RO.community;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniContentIdRO {
    //必须为string，返回给app后是uuid无法变为int
    private Integer id;
    private String creatorSocialuniId;
    private String creatorContentUnionId;

    public SocialuniContentIdRO(Integer id, String creatorSocialuniId, String creatorContentUnionId) {
        this.id = id;
        this.creatorSocialuniId = creatorSocialuniId;
        this.creatorContentUnionId = creatorContentUnionId;
    }

    public SocialuniContentIdRO(SocialuniContentIdRO socialuniContentIdRO) {
        this.id = socialuniContentIdRO.getId();
        this.creatorSocialuniId = socialuniContentIdRO.getCreatorSocialuniId();
        this.creatorContentUnionId = socialuniContentIdRO.getCreatorContentUnionId();
    }
}
