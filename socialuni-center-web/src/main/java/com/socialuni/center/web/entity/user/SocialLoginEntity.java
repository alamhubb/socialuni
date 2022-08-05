package com.socialuni.center.web.entity.user;/*
package com.socialuni.sdk.entity;

import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.entity.model.DO.user.SocialUserFansDetailDO;
import com.socialuni.sdk.repository.SocialUserFansDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialLoginEntity {
    @Resource
    private ThirdUserLoginEntity thirdUserLoginEntity;
    @Resource
    private SocialUserFansDetailRepository socialUserFansDetailRepository;
    @Resource
    private UserDetailRepository userDetailRepository;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;

    //创建用户详情信息，并生成token，返回记录
    public SocialUserFansDetailDO login(UserDO mineUser) {
        //创建或返回
        SocialUserFansDetailDO socialUserFansDetailDO = socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);


        return socialUserFansDetailDO;
    }

}
*/
