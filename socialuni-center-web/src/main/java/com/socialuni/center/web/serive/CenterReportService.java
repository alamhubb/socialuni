package com.socialuni.center.web.serive;

import com.socialuni.center.web.feignAPI.SocialuniReportAPI;
import com.socialuni.center.web.feignAPI.SocialuniUserAPI;
import com.socialuni.center.web.model.QO.CenterReportAddQO;
import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.repository.dev.DevAccountRepository;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UniAPIUtils;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.domain.report.SoicialReportAddDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;
    @Resource
    SocialuniReportAPI socialuniReportAPI;
    @Resource
    SocialuniUserAPI socialuniUserAPI;
    @Resource
    UniContentUnionIdRepository uniContentUnionIdRepository;
    @Resource
    DevAccountRepository devAccountRepository;


    public ResultRO<String> addReport(CenterReportAddQO centerReportAddQO) {
        Integer contentId = UnionIdDbUtil.getUnionIdByUidNotNull(centerReportAddQO.getContentId());

        SocialReportAddQO socialReportAddQO = centerReportAddQO.toSocialQO(contentId);

        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见

        return ResultRO.success(centerTalkRO);

    }
}