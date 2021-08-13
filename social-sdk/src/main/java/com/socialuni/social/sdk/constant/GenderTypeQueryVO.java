package com.socialuni.social.sdk.constant;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
@Data
public class GenderTypeQueryVO {
    //查询的时候选择的，可以选择仅女生，仅男生
    public final String userGender;
    public final String talkVisibleGender;

    public GenderTypeQueryVO(String userGender, String talkGender) {
        this.userGender = userGender;
        this.talkVisibleGender = talkGender;
    }
}
