package com.socialuni.social.sdk.logic.dispatch;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.sdk.logic.domain.user.follow.SocialQueryUserFollowsDomain;
import com.socialuni.social.sdk.logic.domain.user.follow.SocialUserFollowDomain;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.model.QO.follow.SocialuniFollowAddQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniFollowDispatch {
    @Resource
    private SocialQueryUserFollowsDomain socialQueryUserFollowsDomain;
    @Resource
    private SocialUserFollowDomain socialUserFollowDomain;

    public ResultRO<Void> addFlow(SocialuniFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer followUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getBeUserId());

        socialUserFollowDomain.addFlow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public ResultRO<Void> cancelFollow(SocialuniFollowAddQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        Integer followUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getBeUserId());
        socialUserFollowDomain.cancelFollow(mineUserId, followUserId);
        return new ResultRO<>();
    }

    public List<SocialuniUserFollowDetailListRO> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        List<SocialuniUserFollowDetailListRO> map = socialQueryUserFollowsDomain.queryUserFollows(socialuniPageQueryQO);

        return map;
    }
}
