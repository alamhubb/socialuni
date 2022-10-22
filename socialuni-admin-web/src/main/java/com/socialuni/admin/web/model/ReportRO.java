package com.socialuni.admin.web.model;


import com.socialuni.admin.web.constant.AdminAuditResultType;
import com.socialuni.admin.web.factory.ReportContentROFactory;
import com.socialuni.social.sdk.constant.socialuni.SocialuniAuditContentType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.report.sdk.model.ReportModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import com.socialuni.social.sdk.dao.repository.KeywordsTriggerDetailRepository;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Component
public class ReportRO {
    private Integer id;

// 第一版本只考虑talk


    //加入一列作为外键
    private ReportContentVO talk;

// 关联类型，关联的是说说，评论，匹配，用户信息


    private String contentType;
    private List<ReportDetailVO> childReports;
    private ReportUserVO user;
    private Date updateTime;
    private String status;
    private String violateType;
    private Boolean checked;
    private Boolean hasKeyword;
    private String checkText;
    private String auditNote;
    //审核内容类型，举报，成年图片
    private String auditContentType;
    private List<KeywordsTriggerDetailDO> triggerKeywords;

    public ReportRO() {
    }

    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;

    @Resource
    public void setKeywordsTriggerDetailRepository(KeywordsTriggerDetailRepository keywordsTriggerDetailRepository) {
        ReportRO.keywordsTriggerDetailRepository = keywordsTriggerDetailRepository;
    }

    public ReportRO(ReportModel reportModel) {
        this.id = reportModel.getId();
        this.talk = ReportContentROFactory.getReportContentVO(reportModel.getContentType(), reportModel.getContentId());
//        this.reportContentType = reportContentType;

//        this.childReports = reportDO.getChildReports().stream().map(ReportDetailVO::new).collect(Collectors.toList());
        this.user = new ReportUserVO(SocialuniUserUtil.getUserNotNull(reportModel.getContentUserId()));
//        this.updateTime = new Date();
//        this.status = reportDO.getStatus();
        this.checked = true;
        this.violateType = AdminAuditResultType.noViolation;
        this.triggerKeywords = new ArrayList<>();
        this.auditContentType = SocialuniAuditContentType.report;
        if (reportModel.getId() != null) {
            this.triggerKeywords = keywordsTriggerDetailRepository.findAllByReportId(reportModel.getId());
        }
    }

    public ReportRO(SocialuniTalkHasUnderageImgAuditDO imgAuditDO) {
        this.id = imgAuditDO.getId();
        this.talk = ReportContentROFactory.getReportContentVO(SocialuniContentType.talk, imgAuditDO.getTalkId());
//        this.reportContentType = reportContentType;

//        this.childReports = reportDO.getChildReports().stream().map(ReportDetailVO::new).collect(Collectors.toList());
        this.user = new ReportUserVO(SocialuniUserUtil.getUserNotNull(imgAuditDO.getUserId()));
//        this.updateTime = new Date();
//        this.status = reportDO.getStatus();
        this.checked = true;
        this.violateType = AdminAuditResultType.adult;
        this.auditContentType = SocialuniAuditContentType.underageImg;
    }

    public ReportRO(SocialUnionContentBaseDO modelDO) {
        this.talk = ReportContentROFactory.getReportContentVO(modelDO.getContentType(), modelDO.getUnionId());
        this.user = new ReportUserVO(SocialuniUserUtil.getUserNotNull(modelDO.getUserId()));
        this.triggerKeywords = new ArrayList<>();
        this.violateType = modelDO.getViolateType();
        this.auditContentType = SocialuniAuditContentType.report;
//        this.checked = true;
//        this.violateType = ViolateType.noViolation;
    }
}
