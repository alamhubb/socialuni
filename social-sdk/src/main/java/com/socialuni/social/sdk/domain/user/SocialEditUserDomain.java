package com.socialuni.social.sdk.domain.user;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.sdk.platform.tencent.TencentCloud;
import com.socialuni.social.sdk.platform.weixin.HttpResult;
import com.socialuni.social.sdk.repository.UserRepository;
import com.socialuni.social.sdk.service.comment.IllegalWordService;
import com.socialuni.social.sdk.utils.GenderUtil;
import com.socialuni.social.sdk.utils.WxUtil;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
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

    public SocialMineUserDetailRO editUser(SocialUserEditQO socialUserEditQO, UserDO mineUser) {

        //昵称
        String nickname = socialUserEditQO.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new SocialBusinessException("昵称不能为空");
        }
        if (nickname.length() > 6) {
            throw new SocialBusinessException("昵称长度不能大于6");
        } else {
            illegalWordService.checkHasIllegals(nickname);
            //校验内容是否违规
            if (TencentCloud.textIsViolation(nickname)) {
                throw new SocialBusinessException("昵称包含违规内容，禁止修改，请修改后重试");
            }
            HttpResult wxResult = WxUtil.checkContentWxSec(nickname);
            if (wxResult.hasError()) {
                throw new SocialBusinessException("昵称包含违规内容，禁止修改，请修改后重试");
            }
            mineUser.setNickname(StringUtils.substring(nickname, 0, 6));
        }

        String editGender = socialUserEditQO.getGender();

        GenderUtil.checkUserEditGenderType(editGender);

        mineUser.setGender(editGender);

        //生日，年龄
        String birthday = socialUserEditQO.getBirthday();
        if (StringUtils.isNotEmpty(birthday)) {
            mineUser.setBirthday(birthday);
            mineUser.setAge(BirthdayAgeUtil.getAgeByBirth(birthday));
        }

        String userCity = socialUserEditQO.getCity();
        //保存地区名
        if (StringUtils.isNotEmpty(userCity)) {
            if (userCity.length() > 10) {
                throw new SocialBusinessException("市县区名称长度不能大于10");
            } else {
                //校验内容是否违规
                illegalWordService.checkHasIllegals(userCity);

                if (TencentCloud.textIsViolation(userCity)) {
                    throw new SocialBusinessException("地区名称违规");
                }
                HttpResult wxResult = WxUtil.checkContentWxSec(userCity);
                if (wxResult.hasError()) {
                    throw new SocialBusinessException("地区名称违规");
                }
                mineUser.setCity(userCity);
            }
        }
        mineUser.setUpdateTime(new Date());

        mineUser = userRepository.save(mineUser);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);
        return socialMineUserDetailRO;
    }
}
