package com.qingchi.web.model.RO;

import com.socialuni.api.model.RO.user.CenterContentUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class TalkUserRO extends CenterContentUserRO {
    //是否为vip，
    private Boolean vipFlag;
    //已开vip多少个月
    private Integer loveValue;
    private Integer justiceValue;
    private Integer gradeLevel;
    private Integer wealthLevel;

    public TalkUserRO(CenterContentUserRO userRO) {
        super(userRO);
    }
}
