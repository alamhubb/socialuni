package com.socialuni.sdk.serive;

import com.socialuni.sdk.domain.talk.*;
import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
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