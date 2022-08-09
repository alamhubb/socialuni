package com.socialuni.center.web.serive;

import com.socialuni.center.web.feignAPI.SocialuniTalkAPI;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.domain.talk.*;
import com.socialuni.social.api.model.ResultRO;
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
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryTalks();
            talkROS = resultRO.getData();
        } else {
            talkROS = centerHomeTalkQueryDomain.queryHomeTabTalks(null);

            //根据socialTalks 获取 socialtalks
        }
        return new ResultRO<>(talkROS);
    }
}