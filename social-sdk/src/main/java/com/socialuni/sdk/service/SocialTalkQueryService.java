package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.talk.SocialHomeTalkQueryDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTalkQueryQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialTalkQueryService {
    @Resource
    private SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        return socialHomeTalkQueryDomain.queryHomeTalks(SocialUserUtil.getMineUser());
    }

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTalkQueryQO queryVO) {
        //获取当前用户
        UserDO mineUser = SocialUserUtil.getMineUser();
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryVO.getTalkIds(), queryVO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }
}