package com.socialuni.sdk.repository;

import com.socialuni.sdk.model.DO.base.BaseModelDO;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface CommentRepository extends JpaRepository<SocialCommentDO, Integer> {
    BaseModelDO findOneByUnionIdAndStatus(Integer id, String status);

    SocialCommentDO findOneByUnionId(Integer id);

    SocialCommentDO findOneBySocialuniUid(String id);

    @Caching(evict = {
            //用户的talks肯定变化了
            //新增一条数据肯定所有数据清空，数据的显示数据变了
            @CacheEvict(cacheNames = "talkById", key = "#comment.talkId"),
            //新增一条数据肯定所有数据清空，数据的显示数据变了
            @CacheEvict(cacheNames = "talkComments5", key = "#comment.talkId"),
            @CacheEvict(cacheNames = "talkComments50", key = "#comment.talkId"),
            @CacheEvict(cacheNames = "commentComments3", key = "#comment.parentCommentId", condition = "#comment.parentCommentId!=null"),
            @CacheEvict(cacheNames = "commentComments50", key = "#comment.parentCommentId", condition = "#comment.parentCommentId!=null")
    })
    SocialCommentDO save(SocialCommentDO comment);

    //    展示评论
    @Cacheable(cacheNames = "talkComments5", key = "{#talkId}")
    List<SocialCommentDO> findTop5ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(Integer talkId, List<String> status);


    List<SocialCommentDO> findTop10ByUserIdOrderByUpdateTimeDesc(Integer userId);

    //    talk详情页展示评论
    @Cacheable(cacheNames = "talkComments50", key = "{#talkId}")
    List<SocialCommentDO> findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(Integer talkId, List<String> status);

    @Cacheable(cacheNames = "commentComments3", key = "{#commentId}")
    List<SocialCommentDO> findTop3ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(Integer commentId, List<String> status);

    //   展示子回复,避免性能问题，限制最多50条，再多不如私聊了
    @Cacheable(cacheNames = "commentComments50", key = "{#commentId}")
    List<SocialCommentDO> findTop50ByParentCommentIdAndStatusInOrderByUpdateTimeDesc(Integer commentId, List<String> status);

    @Transactional
    @Modifying
    @Query("update SocialCommentDO t set t.status = '" + CommonStatus.delete + "' where t.userId=:userId and t.status in (:status)")
    Integer updateUserCommentStatusIn(@Param("userId") Integer userId, @Param("status") List<String> status);

    Optional<SocialCommentDO> findOneByUnionIdAndStatusIn(Integer id, List<String> status);

    //获取最新的评论
    SocialCommentDO findFirstByTalkIdOrderByIdDesc(Integer talkId);


    List<SocialCommentDO> findByParentCommentId(Integer commentId);


    //查询关键词触发次数时使用
    Page<SocialCommentDO> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);
}