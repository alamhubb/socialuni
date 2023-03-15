package com.socialuni.social.user.sdk.dao.utils;

import cn.hutool.core.util.ReUtil;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserExpandRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.utils.GenderUtil;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialuniUserDOUtil {

    static SocialuniUserRepository socialuniUserRepository;

    @Resource
    public void setSocialuniUserRepository(SocialuniUserRepository socialuniUserRepository) {
        SocialuniUserDOUtil.socialuniUserRepository = socialuniUserRepository;
    }

    public static SocialuniUserDo save(SocialuniUserDo socialuniUserDo) {
        //一个

        //注册时校验， 如果不符合规则，改为未命名。

        //最后一层直接就是不符合就禁止保存了

        String nickname = socialuniUserDo.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new SocialBusinessException("昵称不能为空");
        }
        if (nickname.length() > 6) {
            throw new SocialBusinessException("昵称长度不能大于6");
        }
        boolean containsSpecialChars = ReUtil.contains("[^a-zA-Z0-9\\u4E00-\\u9FA5]", nickname);
        if (containsSpecialChars) {
            throw new SocialBusinessException("昵称不允许包含特殊字符");
        }

        String editGender = socialuniUserDo.getGender();

        GenderUtil.checkUserEditGenderType(editGender);

        //生日，年龄
        String birthday = socialuniUserDo.getBirthday();
        if (StringUtils.isEmpty(birthday)) {
            throw new SocialBusinessException("生日不能为空");
        }
        int age = BirthdayAgeUtil.getAgeByBirth(birthday);

        if (age < 18) {
            throw new SocialBusinessException("年龄不能小于18岁");
        } else if (age > 50) {
            //临时解决方案，初始时间1970 如果小于1970注册im系统会存在问题
            throw new SocialBusinessException("年龄不能大于50岁");
        }

        String userCity = socialuniUserDo.getCity();
        //保存地区名
        if (StringUtils.isNotEmpty(userCity)) {
            if (userCity.length() > 10) {
                throw new SocialBusinessException("市县区名称长度不能大于10");
            }
        }
        socialuniUserDo = socialuniUserRepository.savePut(socialuniUserDo);


//        private String nickname;
//        private String avatar;
//        private String gender;
//        private String birthday;
//        private Integer age;
//        private String city;

        return socialuniUserDo;
    }
}
