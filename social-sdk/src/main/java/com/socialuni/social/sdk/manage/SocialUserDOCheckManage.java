/*
package com.socialuni.sdk.manage;

import com.socialuni.sdk.constant.GenderType;
import com.socialuni.user.model.DO.UniUserDO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.qingchi.web.constant.CommonConst;
import com.socialuni.sdk.utils.AgeUtils;
import com.qingchi.web.constant.DateUtils;
import com.socialuni.sdk.utils.GenderUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class CommonUserDOCheckManage {
    @Value("${social.default-user-avatar:https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/static/uploadimgmini.png!avatar}")
    private String defaultUserAvatar;

    //设置默认值
    public UniUserDO checkSetDefaultValue(UniUserDO user) {
        //昵称
        if (StringUtils.isEmpty(user.getNickname())) {
            user.setNickname("未设置昵称");
        } else {
            user.setNickname(StringUtils.substring(user.getNickname(), 0, 30));
        }

        //头像
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(defaultUserAvatar);
        }

        //性别
        if (StringUtils.isEmpty(user.getGender())) {
            //默认设置为女生，因为女生不介意当男生，而男生介意当女生，所以男生会主动修改，
            // 且体现女士优先，让女士更方便
            user.setGender(GenderType.notFilled);
        } else {
            //做兼容处理，男女转为boy、girl
            String editGender = GenderUtil.getUserEditGenderByOldGender(user.getGender());
            ResultRO<String> checkGender = GenderUtil.checkUserEditGenderType(editGender);
            if (checkGender.hasError()) {
                user.setGender(GenderType.notFilled);
            }
            //做兼容处理，男女转为boy、girl
            user.setGender(editGender);
        }

        //生日、年龄
        if (StringUtils.isEmpty(user.getBirthday())) {
            //生日为空,年龄为空
            if (ObjectUtils.isEmpty(user.getAge())) {
                //设为默认值
                user.setAge(CommonConst.defaultAge);
            }
            //根据年龄生成生日
            user.setBirthday(AgeUtils.getYearBirthDateByAge(user.getAge()));
        } else {
            //生日不为空，则格式化
            user.setBirthday(DateUtils.getBirthDateByDateStr(user.getBirthday()));
            //根据生日生成年龄
            user.setAge(AgeUtils.getAgeByBirth(user.getBirthday()));
        }

        //城市
        if (user.getCity() == null) {
            user.setCity("");
        } else {
            user.setCity(StringUtils.substring(user.getCity(), 0, 14));
        }

        //更新时间
        if (ObjectUtils.isEmpty(user.getUpdateTime())) {
            user.setUpdateTime(new Date());
        }

        //创建日期不能改，只能创建的时候设置
        */
/*if (ObjectUtils.isEmpty(user.getCreateTime())) {
            user.setCreateTime(new Date());
        }*//*


        if (user.getPhoneCountryCode() == null) {
            user.setPhoneCountryCode("");
        } else {
            user.setPhoneCountryCode(StringUtils.substring(user.getPhoneCountryCode(), 0, 6));
        }
        if (user.getPhoneNum() == null) {
            user.setPhoneNum("");
        } else {
            user.setPhoneNum(StringUtils.substring(user.getPhoneNum(), 0, 11));
        }
        return user;
    }
}
*/
