package com.qingchi.web.service.talk;


import com.qingchi.web.domain.talk.TalkDeleteDomain;
import com.qingchi.web.domain.talk.TalkPostDomain;
import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.sdk.constant.CommonConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TalkService {
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;
    @Resource
    TalkPostDomain talkPostDomain;
    @Resource
    TalkDeleteDomain talkDeleteDomain;

    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<CenterTalkRO>> queryHomeTalks() {
        ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryTalks();
        return resultRO;
    }

    @Deprecated
    //兼容旧版本的查询，查询非关注tab的动态列表
    public ResultRO<List<CenterTalkRO>> queryHomeTabTalks(CenterHomeTabTalkQueryQO queryQO) {
        //兼容旧版本
        String gender = queryQO.getGender();
        //仅做对旧版本的兼容处理,查询的兼容
        String mapGender = GenderType.GenderTypeTalkQueryOldMap.get(gender);
        if (mapGender != null) {
            queryQO.setGender(mapGender);
        }
        //如果为初始化，兼容旧版本，则改为全中国
        if (CommonConst.initAdCode.equals(queryQO.getAdCode())) {
            queryQO.setAdCode(CommonConst.chinaDistrictCode);
        }

        ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryTalks(queryQO);
        return resultRO;
    }

    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        talkPostDomain.postTalk(talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(CenterTalkIdQO talkIdQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        talkDeleteDomain.deleteTalk(talkIdQO);
        return new ResultRO<>();
    }

    public ResultRO<CenterTalkRO> queryTalkDetail(CenterTalkIdQO centerTalkIdQO) {
        ResultRO<CenterTalkRO> resultRO = socialuniTalkAPI.queryTalkDetail(centerTalkIdQO);
        return resultRO;
    }

    public ResultRO<List<CenterTalkRO>> queryUserTalks(CenterUserTalkQueryQO queryQO) {
        ResultRO<List<CenterTalkRO>> resultRO = socialuniTalkAPI.queryUserTalks(queryQO);
        return resultRO;
    }
}