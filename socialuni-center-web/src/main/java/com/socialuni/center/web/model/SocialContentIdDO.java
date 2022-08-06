package com.socialuni.center.web.model;

import com.socialuni.center.web.model.DO.SocialContentIdCO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialContentIdDO extends SocialContentIdCO implements Serializable {
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    public SocialContentIdDO(String contentType) {
        super( contentType);
        this.createTime = new Date();
    }

    public SocialContentIdDO(Integer id, String contentType) {
        super(id, contentType);
        this.createTime = new Date();
    }

    public SocialContentIdDO(SocialContentIdDO socialuniContentIdRO) {
        super(socialuniContentIdRO.getId(), socialuniContentIdRO.getContentType());
        this.createTime = socialuniContentIdRO.getCreateTime();
    }
}