package com.socialuni.social.sdk.logic.service.talk;

import com.socialuni.social.common.sdk.feignAPI.community.SocialuniTalkAPI;
import com.socialuni.social.community.sdk.logic.domain.talk.*;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.community.sdk.constant.TalkTabType;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.common.sdk.utils.PositionUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.UniAPIUtils;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniTalkService {
    @Resource
    SocialuniHomeTalkQueryDomain centerHomeTalkQueryDomain;
    @Resource
    SocialuniTalkPostDomain centerTalkPostDomain;
    @Resource
    SocialuniTalkDeleteDomain centerTalkDeleteDomain;
    @Resource
    SocialuniTalkDetailDomain centerTalkDetailDomain;
    @Resource
    SocialuniUserTalkQueryDomain centerUserTalkQueryDomain;
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;
    @Resource
    SocialuniPostTalkDomain socialTalkPostDomain;
    @Resource
    TalkQueryStore talkQueryStore;

    @Resource
    SocialuniHomeTalkQueryDomain socialHomeTalkQueryDomain;

    /**
     * @return
     * @see SocialuniHomeTalkQueryDomain#queryHomeTabTalks(SocialuniHomeTabTalkQueryQO)
     * @deprecated
     */
    @Deprecated
    public ResultRO<List<SocialuniTalkRO>> queryStickTalks() {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTalkAPI.queryStickTalks();
        }
        List<SocialuniTalkRO> list = socialHomeTalkQueryDomain.queryStickTalks();
        return ResultRO.success(list);
    }

    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialuniTalkRO>> queryTalks() {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTalkAPI.queryTalks();
        }
        SocialuniHomeTabTalkQueryQO queryQO = new SocialuniHomeTabTalkQueryQO();
        //获取当前用户
        queryQO.setHomeTabName(TalkTabType.home_name);
        //如果经纬度为空
        SocialuniRectangleRO rectangleVO = PositionUtil.getRectangle();
        if (rectangleVO != null) {
            queryQO.setLon(rectangleVO.getLon());
            queryQO.setLat(rectangleVO.getLat());
        }
        queryQO.setMinAge(SocialuniSystemConst.homeTalkQueryMinAge);
        queryQO.setMaxAge(SocialuniSystemConst.homeTalkQueryMaxAge);
        queryQO.setGender(DevAccountFacade.getAppGenderType());
        return this.queryTalks(queryQO);
    }

    //查询非关注tab的动态列表
    public ResultRO<List<SocialuniTalkRO>> queryTalks(SocialuniHomeTabTalkQueryQO queryQO) {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTalkAPI.queryTalks(queryQO);
        }
        //如果不为测试环境则过滤到测试环境标签的数据
        /*if (DevAccountUtils.getDevIdNotNull() != 3) {
            talkROS = talkROS.stream().filter(talkRO -> {
                List<SocialTalkTagRO> tagROES = talkRO.getTags();
                for (SocialTalkTagRO tagRO : tagROES) {
                    if (tagRO.getName().equals(SocialuniCommonConst.devEnvTagName)) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
        }*/

        List<SocialuniTalkRO> talkROS = centerHomeTalkQueryDomain.queryHomeTabTalks(queryQO);
        return new ResultRO<>(talkROS);
    }

    public ResultRO<SocialuniTalkRO> postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniTalkRO talkRO = centerTalkPostDomain.postTalk(talkPostQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return UniAPIUtils.callUniAPIAndUpdateUid(talkRO, socialuniTalkAPI::postTalk, talkPostQO);
        }
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        return ResultRO.success(talkRO);
    }

    public ResultRO<Void> deleteTalk(SocialuniTalkIdQO talkIdQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        centerTalkDeleteDomain.deleteTalk(talkIdQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTalkAPI.deleteTalk(talkIdQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<SocialuniTalkRO> queryTalkDetail(String talkId) {
        SocialuniTalkRO centerTalkRO;
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            ResultRO<SocialuniTalkRO> centerTalkROResultRO = socialuniTalkAPI.queryTalkDetail(talkId);
            centerTalkRO = centerTalkROResultRO.getData();
        } else {
            centerTalkRO = centerTalkDetailDomain.queryTalkDetail(talkId);
        }
        return ResultRO.success(centerTalkRO);
    }

    public ResultRO<List<SocialuniTalkRO>> queryUserTalks(SocialuniUserTalkQueryQO centerUserTalkQueryQO) {
        List<SocialuniTalkRO> talkROS;
        if (SocialuniSystemConst.serverIsChild()) {
            ResultRO<List<SocialuniTalkRO>> resultRO = socialuniTalkAPI.queryUserTalks(centerUserTalkQueryQO);
            talkROS = resultRO.getData();
        } else {
            talkROS = centerUserTalkQueryDomain.queryUserTalks(centerUserTalkQueryQO);
        }
        return new ResultRO<>(talkROS);
    }
}