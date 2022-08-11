package com.socialuni.sdk.domain.user;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.platform.tencent.TencentCloud;
import com.socialuni.sdk.platform.weixin.HttpResult;
import com.socialuni.sdk.service.comment.IllegalWordService;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialUserEditQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.utils.GenderUtil;
import com.socialuni.sdk.utils.WxUtil;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialEditUserDomain {
    @Resource
    IllegalWordService illegalWordService;
    @Resource
    UserRepository userRepository;

    public SocialMineUserDetailRO editUser(SocialUserEditQO socialUserEditQO, SocialUserDO mineUser) {

        //昵称
        String nickname = socialUserEditQO.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new SocialBusinessException("昵称不能为空");
        }
        if (nickname.length() > 6) {
            throw new SocialBusinessException("昵称长度不能大于6");
        } else {
            String oldNickname = mineUser.getNickname();
            //新旧昵称不一样，则更新
            if (!nickname.equals(oldNickname)) {
                illegalWordService.checkHasIllegals(nickname);
                //校验内容是否违规
                if (TencentCloud.textIsViolation(nickname)) {
                    throw new SocialBusinessException("昵称包含违规内容，禁止修改，请修改后重试");
                }
                HttpResult wxResult = WxUtil.checkTextWxSec(nickname);
                if (wxResult.hasError()) {
                    throw new SocialBusinessException("昵称包含违规内容，禁止修改，请修改后重试");
                }
                mineUser.setNickname(StringUtils.substring(nickname, 0, 6));
            }
        }

        String editGender = socialUserEditQO.getGender();

        GenderUtil.checkUserEditGenderType(editGender);

        mineUser.setGender(editGender);

        //生日，年龄
        String birthday = socialUserEditQO.getBirthday();
        if (StringUtils.isNotEmpty(birthday)) {
            mineUser.setBirthday(birthday);
            int age = BirthdayAgeUtil.getAgeByBirth(birthday);
            if (age < 18) {
                throw new SocialBusinessException("年龄不能小于18岁");
            }
            mineUser.setAge(BirthdayAgeUtil.getAgeByBirth(birthday));
        }

        String userCity = socialUserEditQO.getCity();
        //保存地区名
        if (StringUtils.isNotEmpty(userCity)) {
            if (userCity.length() > 10) {
                throw new SocialBusinessException("市县区名称长度不能大于10");
            } else {
                String oldCity = mineUser.getCity();
                //新旧昵称不一样，则更新
                if (!userCity.equals(oldCity)) {
                    //校验内容是否违规
                    illegalWordService.checkHasIllegals(userCity);

                    if (TencentCloud.textIsViolation(userCity)) {
                        throw new SocialBusinessException("地区名称违规");
                    }
                    HttpResult wxResult = WxUtil.checkTextWxSec(userCity);
                    if (wxResult.hasError()) {
                        throw new SocialBusinessException("地区名称违规");
                    }
                    mineUser.setCity(userCity);
                }
            }
        }
        mineUser.setUpdateTime(new Date());

        mineUser = userRepository.save(mineUser);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return socialMineUserDetailRO;
    }
}
