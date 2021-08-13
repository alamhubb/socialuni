package com.socialuni.social.sdk.service;

import com.socialuni.sdk.domain.talk.*;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.domain.talk.*;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.talk.*;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialTalkService {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    SocialTalkDeleteDomain socialTalkDeleteDomain;
    @Resource
    SocialTalkDetailQueryDomain socialTalkDetailQueryDomain;
    @Resource
    SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;
    @Resource
    SocialUserTalkQueryDomain socialUserTalkQueryDomain;

    //查询非关注tab的动态列表
    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        UserDO mineUser = SocialUserUtil.getMineUser();
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTalks(mineUser);
        return new ResultRO<>(socialTalkROs);
    }

    //查询非关注tab的动态列表
    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryVO) {
        //获取当前用户
        UserDO mineUser = SocialUserUtil.getMineUser();
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryVO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }

    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkPostDomain.postTalk(mineUser, talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkDeleteDomain.deleteTalk(mineUser, talkDeleteQO);
        return new ResultRO<>();
    }

    public ResultRO<SocialTalkRO> queryTalkDetail(SocialTalkIdQO socialTalkIdQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        SocialTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);
        return ResultRO.success(socialTalkRO);
    }

    public ResultRO<List<SocialTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        List<SocialTalkRO> talkROS = socialUserTalkQueryDomain.queryUserTalks(queryQO, mineUser);
        return ResultRO.success(talkROS);
    }
}