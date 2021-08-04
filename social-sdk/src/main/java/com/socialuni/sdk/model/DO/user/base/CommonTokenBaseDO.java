package com.socialuni.sdk.model.DO.user.base;

import com.socialuni.sdk.constant.DateTimeType;
import com.socialuni.sdk.model.DO.CommonContentBaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonTokenBaseDO extends CommonContentBaseDO {

    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String token;

    @Column(nullable = false, updatable = false)
    private Date expiredTime;

    public CommonTokenBaseDO(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
        this.expiredTime = new Date(new Date().getTime() + DateTimeType.quarter);
    }
}