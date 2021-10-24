/*
package com.socialuni.admin.web.model;

import com.qingchi.base.constant.ContentType;
import com.qingchi.base.entity.CommentUtils;
import com.qingchi.base.entity.TalkUtils;
import com.qingchi.base.entity.UserImgUtils;
import com.qingchi.base.model.chat.MessageDO;
import com.qingchi.base.model.report.ReportDO;
import com.qingchi.base.model.talk.CommentDO;
import com.qingchi.base.model.talk.TalkDO;
import com.qingchi.base.model.user.UserImgDO;
import com.qingchi.base.store.MessageStoreUtils;
import com.qingchi.base.store.TagStoreUtils;
import com.qingchi.base.store.TalkImgStoreUtils;
import com.qingchi.base.utils.QingLogger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:07
 *//*

@Data
public class ReportContentVO {
    private Integer id;
    private Integer userId;
    private String content;

    @ApiModelProperty(value = "状态")
    private String status;


    private List<TalkCommentVO> comments;
    */
/**
     * 举报次数
     *//*

    public Integer reportNum;

    private List<ImgVO> imgs;
    private List<TalkTagVO> tags;


    */
/* public ReportContentVO(TalkDO talkDO) {
         if (talkDO != null) {
             this.id = talkDO.getId();
             this.content = talkDO.getContent();
             this.reportNum = talkDO.getReportNum();
             //前台展示图片使用
             this.userId = talkDO.getUserId();
             this.comments = talkDO.getComments().stream().filter((CommentDO commentDO) -> commentDO.getUser().getId().equals(userId)).map(TalkCommentVO::new).collect(Collectors.toList());
             this.tags = talkDO.getTags().stream().map(TalkTagVO::new).collect(Collectors.toList());
         }
     }
 *//*


    public ReportContentVO() {
    }

    public ReportContentVO(ReportDO reportDO) {
        String reportContentType = reportDO.getReportContentType();
        if (reportContentType.equals(ContentType.talk)) {
            TalkDO talkDO = TalkUtils.getTalkById(reportDO.getTalkId());
            this.id = talkDO.getId();
            this.content = talkDO.getContent();
            this.reportNum = talkDO.getReportNum();
            //前台展示图片使用
            this.userId = talkDO.getUserId();
            this.comments = CommentUtils.getAll(talkDO.getId()).stream().filter((CommentDO commentDO) -> commentDO.getUserId().equals(userId)).map(TalkCommentVO::new).collect(Collectors.toList());
            this.tags = TagStoreUtils.getTagsByTalkId(talkDO.getId()).stream().map(TalkTagVO::new).collect(Collectors.toList());
            this.status = talkDO.getStatus();
            this.imgs = ImgVO.talkImgDOToVOS(TalkImgStoreUtils.findTop3ByTalkId(talkDO.getId()));
        } else if (reportContentType.equals(ContentType.comment)) {
            CommentDO commentDO = CommentUtils.get(reportDO.getCommentId());
            this.id = commentDO.getId();
            this.content = commentDO.getContent();
            this.reportNum = commentDO.getReportNum();
            //前台展示图片使用
            this.userId = commentDO.getUserId();
            this.status = commentDO.getStatus();
        } else if (reportContentType.equals(ContentType.userImg)) {

            UserImgDO userImg = UserImgUtils.find(reportDO.getUserImgId());
            this.userId = userImg.getUserId();
            this.imgs = ImgVO.userImgDOToVOS(Collections.singletonList(userImg));
            this.status = userImg.getStatus();
        } else if (reportContentType.equals(ContentType.message)) {
            MessageDO message = MessageStoreUtils.findById(reportDO.getMessageId());
            this.userId = message.getUserId();
            this.content = message.getContent();
            this.status = message.getStatus();
        } else {
            QingLogger.logger.error("未知的举报类型");
        }
    }
}
*/
