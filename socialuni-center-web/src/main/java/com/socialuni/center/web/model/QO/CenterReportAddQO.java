package com.socialuni.center.web.model.QO;

import com.socialuni.center.web.model.QO.SocialReportAddQO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:24
 */
@Data
public class CenterReportAddQO {
    private String contentId;
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

    public SocialReportAddQO toSocialQO(Integer contentId) {
        SocialReportAddQO socialReportAddQO = new SocialReportAddQO();
        socialReportAddQO.setContentId(contentId);
        socialReportAddQO.setContent(this.content);
        socialReportAddQO.setReportType(this.reportType);
        socialReportAddQO.setReportContentType(this.reportContentType);
        socialReportAddQO.setCause(this.cause);
        return socialReportAddQO;
    }
}
