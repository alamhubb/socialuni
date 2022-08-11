package com.socialuni.sdk.model.DO;

import com.socialuni.social.constant.CommonStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
public class SocialContentBaseDO extends SocialCommonBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public SocialContentBaseDO() {
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}