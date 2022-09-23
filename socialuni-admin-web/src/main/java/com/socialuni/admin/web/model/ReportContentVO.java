package com.socialuni.admin.web.model;

import com.socialuni.social.sdk.model.RO.community.tag.TagRO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkImgRO;
import lombok.Data;

import java.util.List;

@Data
public class ReportContentVO {
    private Integer id;
    private Integer userId;
    private String content;
//    private String violateType;

    private String status;


    private List<SocialuniCommentRO> comments;
//举报次数


    public Integer reportNum;

    private List<SocialuniTalkImgRO> imgs;
    private List<TagRO> tags;

}
