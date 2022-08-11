package com.socialuni.sdk.serive;

import com.socialuni.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.sdk.model.QO.SocialReportAddQO;
import com.socialuni.sdk.repository.UniContentUnionIdRepository;
import com.socialuni.sdk.repository.dev.DevAccountRepository;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.api.model.ResultRO;
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

        return soicialReportAddDomain.addReport(socialReportAddQO);

    }
}