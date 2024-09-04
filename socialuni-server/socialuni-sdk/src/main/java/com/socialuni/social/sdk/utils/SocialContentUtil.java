package com.socialuni.social.sdk.utils;/*
package com.socialuni.center.web.utils.model;

import com.socialuni.entity.model.DO.comment.CommentDO;
import com.socialuni.sdk.model.DO.content.SocialContentDO;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.repository.CommentRepository;
import com.socialuni.sdk.repository.SocialContentRepository;
import com.socialuni.sdk.repository.TalkRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialContentUtil {
    private static SocialContentRepository socialContentRepository;
    private static CommentRepository commentRepository;
    private static TalkRepository talkRepository;

    @Resource
    public void setSocialContentRepository(SocialContentRepository socialContentRepository) {
        SocialContentUtil.socialContentRepository = socialContentRepository;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialContentUtil.commentRepository = commentRepository;
    }

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        SocialContentUtil.talkRepository = talkRepository;
    }

    public static SocialContentDO get(Long contentId) {
        return socialContentRepository.findOneById(contentId);
    }

    public static CommentDO getComment(Long contentId) {
        return commentRepository.findOneById(contentId);
    }

    public static TalkDO getTalk(Long contentId) {
        return talkRepository.findOneById(contentId);
    }
}
*/
