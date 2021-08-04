package com.socialuni.social.model.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:24
 */
@Data
public class ReportAddQO {
    //加入一列作为外键
    private Integer talkId;
    //加入一列作为外键
    private Integer commentId;
    //加入一列作为外键
    private Integer messageId;
    private Integer contentId;
    /**
     * 举报的用户照片id
     */
    private List<String> userImgIds;
    private Integer userImgId;
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
     * 被举报信息的版本号
     */
    private Integer infoVersionNo;
    /**
     * 举报时的备注信息
     */
    private String content;
    //新版本用这个，不用上面那个了content
    private String cause;
}
