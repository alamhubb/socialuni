package com.socialuni.social.common.sdk.model;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */

@Data
@NoArgsConstructor
public class SocialuniContentBaseModel extends SocialuniUserInfoBaseModel implements Serializable {
    private String status;

    private Date updateTime;

    public SocialuniContentBaseModel(Integer userId) {
        super(userId);
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}