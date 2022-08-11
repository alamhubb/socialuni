package com.socialuni.sdk.constant;

import com.socialuni.social.constant.GenderType;

import java.util.HashMap;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class GenderTypeVO {
    //全部，所有性别用户，所有可见性别talk
    public static final GenderTypeQueryVO allVO = new GenderTypeQueryVO(GenderType.all, GenderType.all);
    //女生发布，女生用户，所有可见性别talk
    public static final GenderTypeQueryVO girlVO = new GenderTypeQueryVO(GenderType.girl, GenderType.all);
    //仅女生可见，女生用户，仅女生可见talk
    public static final GenderTypeQueryVO onlyGirlVO = new GenderTypeQueryVO(GenderType.girl, GenderType.girl);
    //男生发布，男生用户，所有可见性别talk
    public static final GenderTypeQueryVO boyVO = new GenderTypeQueryVO(GenderType.boy, GenderType.all);
    //仅男生可见，男生用户，仅男生可见talk
    public static final GenderTypeQueryVO onlyBoyVO = new GenderTypeQueryVO(GenderType.boy, GenderType.boy);

    //查询时使用，获取gender对应的userGender和talkGender
    public static final HashMap<String, GenderTypeQueryVO> GenderTypeQueryMap = new HashMap<String, GenderTypeQueryVO>() {{
        put(GenderType.all, GenderTypeVO.allVO);
        put(GenderType.girl, GenderTypeVO.girlVO);
        put(GenderType.onlyGirl, GenderTypeVO.onlyGirlVO);
        put(GenderType.boy, GenderTypeVO.boyVO);
        put(GenderType.onlyBoy, GenderTypeVO.onlyBoyVO);
    }};
}
