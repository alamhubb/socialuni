package com.socialuni.admin.web.model;

import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkImgRO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import lombok.Data;

import java.util.List;

@Data
public class ReportContentVO {
    private Integer id;
    private Integer userId;
    private String content;

    private String status;


    private List<CenterCommentRO> comments;
//举报次数


    public Integer reportNum;

    private List<SocialTalkImgRO> imgs;
    private List<TagRO> tags;

}
