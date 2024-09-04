package com.socialuni.social.follow.logic.serivce;

import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.follow.api.SocialuniFollowAPI;
import com.socialuni.social.follow.logic.domain.SocialQueryUserFollowsDomain;
import com.socialuni.social.follow.logic.domain.SocialUserFollowDomainInterface;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowAddQO;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniFollowService {
    @Resource
    private SocialUserFollowDomainInterface socialUserFollowDomainInterface;
    @Resource
    private SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;

    @Resource
    SocialuniFollowAPI socialuniFollowAPI;

    public ResultRO<Void> addFollow(SocialuniUserFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        socialUserFollowDomainInterface.addFlow(addQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.hasCenterServer()) {
            return socialuniFollowAPI.addFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialuniUserFollowAddQO addQO) {
        //有问题，应该关注完刷新前台用户
        socialUserFollowDomainInterface.cancelFollow(addQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.hasCenterServer()) {
            return socialuniFollowAPI.cancelFollow(addQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<List<SocialuniUserFollowDetailListRO>> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        //如果应用，则调用中心
        if (SocialuniSystemConst.hasCenterServer()) {
            return socialuniFollowAPI.queryUserFollows(socialuniPageQueryQO);
        }
        List<SocialuniUserFollowDetailListRO> map = socialQueryUserFollowsDomain.queryUserFollows(socialuniPageQueryQO);
        return new ResultRO<>(map);
    }
}
