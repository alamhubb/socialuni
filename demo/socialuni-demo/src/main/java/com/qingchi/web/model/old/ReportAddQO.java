package com.qingchi.web.model.old;

import com.socialuni.api.model.QO.CenterReportAddQO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:24
 */
@Data
public class ReportAddQO {
    /**
     * 举报类型，政治敏感，暴露，暴力等
     */
    @NotBlank(message = "入参为空异常")
    private String reportType;
    /**
     * 关联类型，关联的是说说，评论，匹配，用户信息
     */
    @NotBlank(message = "入参为空异常")
    private String reportContentType;
    /**
     * 举报时的备注信息
     */
    private String content;
    //新版本用这个，不用上面那个了content
    private String cause;

    private String talkId;
    private String commentId;
    private String messageId;
    private String contentId;
    private List<String> userImgIds;
    private String userImgId;

    private Integer infoVersionNo;

    public CenterReportAddQO toCenterReportQO() {
        CenterReportAddQO centerReport = new CenterReportAddQO();
        if (StringUtils.isNotEmpty(contentId)) {
            centerReport.setContentId(contentId);
        } else if (StringUtils.isNotEmpty(talkId)) {
            centerReport.setContentId(talkId);
        } else if (StringUtils.isNotEmpty(commentId)) {
            centerReport.setContentId(commentId);
        } else if (StringUtils.isNotEmpty(messageId)) {
            centerReport.setContentId(messageId);
        } else if (StringUtils.isNotEmpty(userImgId)) {
            centerReport.setContentId(userImgId);
        }
        centerReport.setReportType(this.reportType);
        centerReport.setReportContentType(this.reportContentType);
        centerReport.setContent(this.content);
        centerReport.setCause(this.cause);
        return centerReport;
    }
}
