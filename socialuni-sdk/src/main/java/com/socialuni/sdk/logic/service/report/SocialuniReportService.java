package com.socialuni.sdk.logic.service.report;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.logic.domain.report.SoicialReportAddDomain;
import com.socialuni.sdk.feignAPI.community.SocialuniReportAPI;
import com.socialuni.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.sdk.dao.repository.SocialuniUnionIdRepository;
import com.socialuni.sdk.dao.repository.dev.DevAccountRepository;
import com.socialuni.sdk.model.QO.SocialuniReportAddQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;
    @Resource
    SocialuniReportAPI socialuniReportAPI;
    @Resource
    SocialuniUserAPI socialuniUserAPI;
    @Resource
    SocialuniUnionIdRepository uniContentUnionIdRepository;
    @Resource
    DevAccountRepository devAccountRepository;

    public ResultRO<String> addReport(SocialuniReportAddQO centerReportAddQO) {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniReportAPI.addReport(centerReportAddQO);
        }

        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        ResultRO<String> resultRO = soicialReportAddDomain.addReport(centerReportAddQO);

        return resultRO;
    }
}