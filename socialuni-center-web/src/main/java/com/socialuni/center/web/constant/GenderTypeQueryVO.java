package com.socialuni.center.web.constant;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
@Data
public class GenderTypeQueryVO {
    public final String talkUserGender;
    //查询的时候选择的，可以选择仅女生，仅男生
    public final String talkVisibleGender;

    public GenderTypeQueryVO(String talkUserGender, String talkGender) {
        this.talkUserGender = talkUserGender;
        this.talkVisibleGender = talkGender;
    }
}
