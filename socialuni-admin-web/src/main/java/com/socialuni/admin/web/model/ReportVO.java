package com.socialuni.admin.web.model;


import com.socialuni.admin.web.factory.ReportContentROFactory;
import com.socialuni.social.entity.model.DO.ReportDO;
import com.socialuni.social.entity.model.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.repository.KeywordsTriggerDetailRepository;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Data
@Component
public class ReportVO {
    private Integer id;

// 第一版本只考虑talk


    //加入一列作为外键
    private ReportContentVO talk;

// 关联类型，关联的是说说，评论，匹配，用户信息


    private String reportContentType;
    private List<ReportDetailVO> childReports;
    private ReportUserVO user;
    private Date updateTime;
    private String status;
    private String violateType;
    private Boolean checked;
    private Boolean hasKeyword;
    private String checkText;
    private String auditNote;
    private List<KeywordsTriggerDetailDO> triggerKeywords;

    public ReportVO() {
    }

    private static KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;

    @Resource
    public void setKeywordsTriggerDetailRepository(KeywordsTriggerDetailRepository keywordsTriggerDetailRepository) {
        ReportVO.keywordsTriggerDetailRepository = keywordsTriggerDetailRepository;
    }

    public ReportVO(ReportDO reportDO) {
        this.id = reportDO.getId();
        this.talk = ReportContentROFactory.getReportContentVO(reportDO);
//        this.reportContentType = reportContentType;

//        this.childReports = reportDO.getChildReports().stream().map(ReportDetailVO::new).collect(Collectors.toList());
        this.user = new ReportUserVO(SocialUserUtil.get(reportDO.getReceiveUserId()));
        this.updateTime = new Date();
        this.status = reportDO.getStatus();
        this.checked = true;
        this.violateType = ViolateType.noViolation;

        this.triggerKeywords = keywordsTriggerDetailRepository.findAllByReportId(reportDO.getId());
    }

}
