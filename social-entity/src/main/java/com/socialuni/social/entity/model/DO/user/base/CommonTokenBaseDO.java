package com.socialuni.social.entity.model.DO.user.base;

import com.socialuni.social.constant.DateTimeType;
import com.socialuni.social.entity.model.DO.base.CommonContentBaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonTokenBaseDO extends CommonContentBaseDO implements Serializable {

    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String token;

    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date expiredTime;

    public CommonTokenBaseDO(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
        this.expiredTime = new Date(new Date().getTime() + DateTimeType.quarter);
    }
}