package com.socialuni.admin.web.model;

import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkImgRO;
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
