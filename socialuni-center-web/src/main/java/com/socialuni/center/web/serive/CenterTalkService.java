package com.socialuni.center.web.serive;

import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.domain.talk.*;
import com.socialuni.center.web.feignAPI.SocialuniTalkAPI;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.center.web.model.QO.talk.CenterTalkIdQO;
import com.socialuni.center.web.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.utils.UniAPIUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterTalkService {
    @Resource
    CenterHomeTalkQueryDomain centerHomeTalkQueryDomain;
    @Resource
    CenterTalkPostDomain centerTalkPostDomain;
    @Resource
    CenterTalkDeleteDomain centerTalkDeleteDomain;
    @Resource
    CenterTalkDetailDomain centerTalkDetailDomain;
    @Resource
    CenterUserTalkQueryDomain centerUserTalkQueryDomain;
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;

    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<CenterTalkRO>> queryTalks() {
        List<CenterTalkRO> talkROS;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryTalks();
            talkROS = resultRO.getData();
        } else {
            talkROS = centerHomeTalkQueryDomain.queryHomeTabTalks(null);
        }
        return new ResultRO<>(talkROS);
    }

    //查询非关注tab的动态列表
    public ResultRO<List<CenterTalkRO>> queryTalks(CenterHomeTabTalkQueryQO queryQO) {
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTalkAPI.queryTalks(queryQO);
        } else {
            List<CenterTalkRO> talkROS = centerHomeTalkQueryDomain.queryHomeTabTalks(queryQO);
            return new ResultRO<>(talkROS);
        }
    }

    public ResultRO<CenterTalkRO> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        CenterTalkRO centerTalkRO;
        UniContentIdRO uniContentIdRO = UniAPIUtils.callUniAPI(ContentType.talk, centerTalkPostDomain::postTalk, socialuniTalkAPI::postTalk, talkPostQO);
        centerTalkRO = (CenterTalkRO) uniContentIdRO;
        return ResultRO.success(centerTalkRO);
    }

    public ResultRO<Void> deleteTalk(CenterTalkIdQO talkIdQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        centerTalkDeleteDomain.deleteTalk(talkIdQO);
        return new ResultRO<>();
    }

    public ResultRO<CenterTalkRO> queryTalkDetail(CenterTalkIdQO talkIdQO) {
        CenterTalkRO centerTalkRO = centerTalkDetailDomain.queryTalkDetail(talkIdQO);
        return new ResultRO<>(centerTalkRO);
    }

    public ResultRO<List<CenterTalkRO>> queryUserTalks(CenterUserTalkQueryQO centerUserTalkQueryQO) {
        List<CenterTalkRO> centerTalkROS = centerUserTalkQueryDomain.queryUserTalks(centerUserTalkQueryQO);
        return new ResultRO<>(centerTalkROS);
    }
}