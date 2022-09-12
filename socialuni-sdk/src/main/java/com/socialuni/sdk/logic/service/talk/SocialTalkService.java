package com.socialuni.sdk.logic.service.talk;

import com.socialuni.sdk.logic.domain.talk.*;
import com.socialuni.sdk.model.QO.community.talk.*;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
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
    SocialuniHomeTalkQueryDomain socialHomeTalkQueryDomain;
    @Resource
    SocialUserTalkQueryDomain socialUserTalkQueryDomain;

    //查询非关注tab的动态列表
    //无参数get请求访问talks，主要为了方便用户体验。
    /*public ResultRO<List<SocialuniTalkRO>> queryHomeTalks() {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialHomeTabTalkQueryQO queryQO = new SocialHomeTabTalkQueryQO();
        queryQO.setHomeTabType(TalkTabType.home_type);
        //如果经纬度为空
        RectangleVO rectangleVO = MapUtil.getRectangle();
        if (rectangleVO != null) {
            queryQO.setLon(rectangleVO.getLon());
            queryQO.setLat(rectangleVO.getLat());
        }
        queryQO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
        queryQO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
        queryQO.setGender(GenderType.all);

        //转换为rolist
        return this.queryHomeTabTalks(queryQO);
    }
*/
    //查询非关注tab的动态列表
    //查询非关注tab的动态列表
    /*public ResultRO<List<SocialuniTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        //转换为rolist
        List<SocialuniTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(queryQO, mineUser);
        return new ResultRO<>(socialTalkROs);
    }*/

    public ResultRO<Void> postTalk(SocialuniTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        socialTalkPostDomain.postTalk(talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        socialTalkDeleteDomain.deleteTalk(mineUser, talkDeleteQO);
        return new ResultRO<>();
    }

    public ResultRO<SocialuniTalkRO> queryTalkDetail(SocialTalkIdQO socialTalkIdQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        SocialuniTalkRO socialTalkRO = socialTalkDetailQueryDomain.queryTalkDetail(socialTalkIdQO, mineUser);
        return ResultRO.success(socialTalkRO);
    }

    public ResultRO<List<SocialuniTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        List<SocialuniTalkRO> talkROS = socialUserTalkQueryDomain.queryUserTalks(queryQO, mineUser);
        return ResultRO.success(talkROS);
    }
}