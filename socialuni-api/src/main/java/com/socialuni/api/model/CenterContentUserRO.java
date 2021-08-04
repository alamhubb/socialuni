package com.socialuni.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterContentUserRO extends CenterListUserRO {
    private Boolean hasFollowed;
    //是否被对方关注
    private Boolean hasBeFollowed;
    private Boolean isMine;

    public CenterContentUserRO(CenterListUserRO userRO) {
        super(userRO);
    }

    public CenterContentUserRO(CenterContentUserRO userRO) {
        super(userRO);
        this.hasFollowed = userRO.getHasFollowed();
        this.hasBeFollowed = userRO.getHasBeFollowed();
        this.isMine = userRO.getIsMine();
    }
}
