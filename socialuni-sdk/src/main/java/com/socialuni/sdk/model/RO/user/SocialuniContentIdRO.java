package com.socialuni.sdk.model.RO.user;

import com.socialuni.sdk.utils.UnionIdDbUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniContentIdRO {
    private String id;

    public SocialuniContentIdRO(String id) {
        this.id = id;
    }

    public SocialuniContentIdRO(String contentType, SocialuniContentIdRO socialuniContentIdRO) {
        this.id = UnionIdDbUtil.createUidByUid(contentType, socialuniContentIdRO.getId());
    }
}
