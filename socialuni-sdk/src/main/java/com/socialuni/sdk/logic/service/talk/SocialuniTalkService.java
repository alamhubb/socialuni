package com.socialuni.sdk.logic.service.talk;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.logic.domain.talk.*;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.model.RectangleVO;
import com.socialuni.sdk.logic.platform.MapUtil;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.UniAPIUtils;
import com.socialuni.social.web.sdk.model.ResultRO;
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
    SocialTalkPostDomain socialTalkPostDomain;

    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialuniTalkRO>> queryTalks() {
        return this.queryTalks(null);
    }

    //查询非关注tab的动态列表
    public ResultRO<List<SocialuniTalkRO>> queryTalks(SocialuniHomeTabTalkQueryQO queryQO) {
        if (queryQO == null) {
            //获取当前用户
            queryQO = new SocialuniHomeTabTalkQueryQO();
            queryQO.setHomeTabType(TalkTabType.home_type);
            //如果经纬度为空
            RectangleVO rectangleVO = MapUtil.getRectangle();
            if (rectangleVO != null) {
                queryQO.setLon(rectangleVO.getLon());
                queryQO.setLat(rectangleVO.getLat());
            }
            queryQO.setMinAge(SocialAppConfig.homeTalkQueryMinAge);
            queryQO.setMaxAge(SocialAppConfig.homeTalkQueryMaxAge);
            queryQO.setGender(DevAccountUtils.getAppGenderType());
            queryQO.setPageNum(0);
        }
        List<SocialuniTalkRO> talkROS;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<List<SocialuniTalkRO>> resultRO = socialuniTalkAPI.queryTalks(queryQO);
            talkROS = resultRO.getData();
        } else {
            talkROS = centerHomeTalkQueryDomain.queryHomeTabTalks(queryQO);
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
        return new ResultRO<>(talkROS);
    }

    public ResultRO<SocialuniTalkRO> postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniTalkRO talkRO = centerTalkPostDomain.postTalk(talkPostQO);
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return UniAPIUtils.callUniAPIAndUpdateUid(talkRO, socialuniTalkAPI::postTalk, talkPostQO);
        }
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        return ResultRO.success(talkRO);
    }

    public ResultRO<Void> deleteTalk(SocialuniTalkIdQO talkIdQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        centerTalkDeleteDomain.deleteTalk(talkIdQO);
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTalkAPI.deleteTalk(talkIdQO);
        }
        return new ResultRO<>();
    }

    public ResultRO<SocialuniTalkRO> queryTalkDetail(String talkId) {
        SocialuniTalkRO centerTalkRO;
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<SocialuniTalkRO> centerTalkROResultRO = socialuniTalkAPI.queryTalkDetail(talkId);
            centerTalkRO = centerTalkROResultRO.getData();
        } else {
            centerTalkRO = centerTalkDetailDomain.queryTalkDetail(talkId);
        }
        return ResultRO.success(centerTalkRO);
    }

    public ResultRO<List<SocialuniTalkRO>> queryUserTalks(SocialuniUserTalkQueryQO centerUserTalkQueryQO) {
        List<SocialuniTalkRO> talkROS;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<List<SocialuniTalkRO>> resultRO = socialuniTalkAPI.queryUserTalks(centerUserTalkQueryQO);
            talkROS = resultRO.getData();
        } else {
            talkROS = centerUserTalkQueryDomain.queryUserTalks(centerUserTalkQueryQO);
        }
        return new ResultRO<>(talkROS);
    }
}