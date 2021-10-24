/*
package com.socialuni.admin.web.model;

import com.qingchi.base.constant.ViolateType;
import com.qingchi.base.model.report.ReportDO;
import com.qingchi.base.model.system.KeywordsTriggerDetailDO;
import com.qingchi.base.repository.keywords.KeywordsTriggerDetailRepository;
import com.qingchi.base.utils.UserUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:07
 *//*

@Data
@Component
public class ReportVO {
    private Integer id;

    */
/**
     * 第一版本只考虑talk
     *//*

    //加入一列作为外键
    private ReportContentVO talk;

    */
/**
     * 关联类型，关联的是说说，评论，匹配，用户信息
     *//*

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
        this.talk = new ReportContentVO(reportDO);
//        this.reportContentType = reportContentType;

//        this.childReports = reportDO.getChildReports().stream().map(ReportDetailVO::new).collect(Collectors.toList());
        this.user = new ReportUserVO(UserUtils.get(reportDO.getReceiveUserId()));
        this.updateTime = new Date();
        this.status = reportDO.getStatus();
        this.checked = true;
        this.violateType = ViolateType.noViolation;

        this.triggerKeywords = keywordsTriggerDetailRepository.findAllByReportId(reportDO.getId());
    }

}
*/
