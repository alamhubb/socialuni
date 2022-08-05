package com.socialuni.center.web.serive;

import com.socialuni.center.web.feignAPI.SocialuniTalkAPI;
import com.socialuni.center.web.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.center.web.model.QO.talk.CenterTalkIdQO;
import com.socialuni.center.web.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.domain.talk.*;
import com.socialuni.center.web.utils.UniAPIUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniTalkService {
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
        if (SocialAppConfig.hasCenterServer()) {
            ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryTalks();
            talkROS = resultRO.getData();


        } else {
            talkROS = centerHomeTalkQueryDomain.queryHomeTalks();

            //根据socialTalks 获取 socialtalks
        }
        return new ResultRO<>(talkROS);
    }
}