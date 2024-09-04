package com.socialuni.social.report.sdk.logic.service;

import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.report.sdk.api.SocialuniReportAPI;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.report.sdk.model.SocialuniReportAddQO;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;
    @Resource
    SocialuniReportAPI socialuniReportAPI;
    @Resource
    SocialuniUnionIdInterface uniContentUnionIdRepository;
    @Resource
    DevAccountInterface devAccountApi;

    public ResultRO<String> addReport(SocialuniReportAddQO centerReportAddQO) {
        //如果应用，则调用中心
        if (SocialuniSystemConst.hasCenterServer()) {
            return socialuniReportAPI.addReport(centerReportAddQO);
        }

        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        ResultRO<String> resultRO = soicialReportAddDomain.addReport(centerReportAddQO);

        return resultRO;
    }
}