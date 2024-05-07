package com.socialuni.social.follow.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO;
import com.socialuni.social.follow.api.SocialuniFollowAPI;
import com.socialuni.social.follow.logic.serivce.SocialuniFollowService;
import com.socialuni.social.follow.model.follow.SocialuniUserFansDetailRO;
import com.socialuni.social.follow.model.follow.SocialuniUserFollowAddQO;
import com.socialuni.social.follow.utils.SocialuniUserFollowInfoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/follow")
public class SocialuniFollowController implements SocialuniFollowAPI {
    @Resource
    SocialuniFollowService centerFollowService;

    @Override
    public ResultRO<List<SocialuniUserFollowDetailListRO>> queryUserFollows(SocialuniPageQueryQO<String> socialuniPageQueryQO) {
        ResultRO<List<SocialuniUserFollowDetailListRO>> resultRO = centerFollowService.queryUserFollows(socialuniPageQueryQO);
        return resultRO;
    }

    @Override
    public ResultRO<Void> addFollow(SocialuniUserFollowAddQO addVO) {
        return centerFollowService.addFollow(addVO);
    }

    @Override
    public ResultRO<Void> cancelFollow(SocialuniUserFollowAddQO addVO) {
        return centerFollowService.cancelFollow(addVO);
    }

    @Override
    public ResultRO<SocialuniUserFansDetailRO> getMineUserFollowDetail() {
        return ResultRO.success(SocialuniUserFollowInfoUtil.getMineUserFollowRelationInfo());
    }

    @Override
    public ResultRO<SocialuniUserFansDetailRO> queryUserFansDetail(String userId) {
        return ResultRO.success(SocialuniUserFollowInfoUtil.getUserFollowRelationInfo(userId));
    }
}