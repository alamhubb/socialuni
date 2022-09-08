package com.socialuni.sdk.logic.service.talk;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.logic.domain.talk.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}