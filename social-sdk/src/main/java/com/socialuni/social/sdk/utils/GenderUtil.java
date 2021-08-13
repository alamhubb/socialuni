package com.socialuni.social.sdk.utils;

import com.socialuni.social.constant.GenderType;
import com.socialuni.social.sdk.constant.GenderTypeQueryVO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.constant.GenderTypeVO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class GenderUtil {
    //仅做对旧版本的兼容处理,查询的兼容
    public static String getTalkQueryGenderByOldGender(String gender) {
        String mapGender = GenderType.GenderTypeTalkQueryOldMap.get(gender);
        if (mapGender != null) {
            return mapGender;
        }
        return gender;
    }

    //仅做对旧版本的兼容处理，修改用户的兼容
    public static String getUserEditGenderByOldGender(String gender) {
        String mapGender = GenderType.GenderTypeUserEditOldMap.get(gender);
        if (mapGender != null) {
            return mapGender;
        }
        return gender;
    }

    //校验query的GenderType是否正确
    public static void checkUserEditGenderType(String gender) {
        String mapGender = GenderType.GenderTypeUserEditMap.get(gender);
        if (mapGender == null) {
            throw new SocialParamsException("错误的性别类型");
        }
    }

    //校验query的GenderType是否正确
    public static GenderTypeQueryVO checkQueryGenderType(String gender) {
        GenderTypeQueryVO queryVO = GenderTypeVO.GenderTypeQueryMap.get(gender);
        if (queryVO == null) {
            throw new SocialParamsException("错误的性别类型");
        }
        return queryVO;
    }

    //appGenderType/addTalk/addTag，一致，可以选择全部，仅女，仅男的动态，和话题
    public static void checkAppOrTalkTagGenderType(String gender) {
        String mapGender = GenderType.GenderTypeMap.get(gender);
        if (mapGender == null) {
            throw new SocialParamsException("错误的性别类型");
        }
    }

    //如果appGender为所有，则gender在列表中就行
    //否则，gender从家兼容中获取，需要与appgender一致，
    // 非all情况下、appgender和visiblegender和userGender要一致，、查询talk、新增talk、新增tag，三处的通用校验，
    public static void checkAppAndVisibleGender(String appGenderType, String talkVisibleGender, String userGender) {
        //校验appGender
        GenderUtil.checkAppOrTalkTagGenderType(appGenderType);

        //校验visibleGender
        GenderUtil.checkAppOrTalkTagGenderType(talkVisibleGender);


        //appgender只有不为全部的时候校验一下，是否与genderType是否相符，为all的时候，无限制
        // 如果appGender不为全部，则代表为单性别app,这种情况校验 appGender需要和genderType一致
        if (!appGenderType.equals(GenderType.all) && !talkVisibleGender.equals(appGenderType)) {
            throw new SocialParamsException("选择的性别与app性别类型不符");
        }

        //如果用户不为null，如果talkGender不为null，则talkGender必须与用户的性别一致，只有同性别才能查询仅本性别可见的内容
        //all/girl/boy

        if (StringUtils.isNotEmpty(userGender) && (!talkVisibleGender.equals(GenderType.all) && !talkVisibleGender.equals(userGender))) {
            throw new SocialParamsException("用户性别与专属动态性别不符");
        }
    }
}
