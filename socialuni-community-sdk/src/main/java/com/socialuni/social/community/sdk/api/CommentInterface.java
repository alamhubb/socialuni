package com.socialuni.social.community.sdk.api;

import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface CommentInterface {
    SocialUnionContentBaseDO findOneByUnionIdAndStatus(Integer id, String status);

    SocialuniCommentModel findOneByUnionId(Integer id);


    SocialuniCommentModel savePut(SocialuniCommentModel comment);

    //    展示评论
    List<?  extends SocialuniCommentModel> findTop5ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(Integer talkId, List<String> status);


    List<?  extends SocialuniCommentModel> findTop10ByUserIdOrderByUpdateTimeDesc(Integer userId);

    //    talk详情页展示评论
    List<?  extends SocialuniCommentModel> findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(Integer talkId, List<String> status);

    List<?  extends SocialuniCommentModel> findTop3ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(Integer commentId, List<String> status);

    //   展示子回复,避免性能问题，限制最多50条，再多不如私聊了
    List<?  extends SocialuniCommentModel> findTop50ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(Integer commentId, List<String> status);

    Integer updateUserCommentStatusIn( Integer userId, List<String> status);

    Optional<?  extends SocialuniCommentModel> findOneByUnionIdAndStatusIn(Integer id, List<String> status);

    //获取最新的评论
    SocialuniCommentModel findFirstByTalkIdOrderByIdDesc(Integer talkId);


    List<?  extends SocialuniCommentModel> findByParentCommentId(Integer commentId);


    //查询关键词触发次数时使用
    Page<?  extends SocialuniCommentModel> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);
}