package com.socialuni.social.report.sdk.api;

import com.socialuni.social.report.sdk.model.ReportModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportApi  {
    ReportModel findOneByContentId(Integer contentId);

//    Optional<ReportDO> findFirstOneByTalkId(Integer talkId);

//    Optional<ReportDO> findFirstOneByCommentId(Integer commentId);

//    Optional<ReportDO> findFirstOneByUserImgId(Integer userImgId);

//    Optional<ReportDO> findFirstOneByMessageId(Integer messageId);

//    List<ReportDO> findTop5ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

    List<?  extends ReportModel> findTop10ByStatusInOrderByCreateTimeAsc(List<String> status);

    List<?  extends ReportModel> findTop10ByContentUserIdOrderByCreateTimeDesc(Integer userId);
    List<?  extends ReportModel> findTop20ByStatusInOrderByCreateTimeAsc(List<String> status);
    List<?  extends ReportModel> findTop20ByStatusInAndDevIdOrderByCreateTimeAsc(List<String> status, Integer devId);

//    List<ReportDO> findTop20ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

//    List<ReportDO> findTop30ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

//    List<ReportDO> findTop10ByReceiveUserIdAndStatusOrderByUpdateTimeDesc(Integer userId, String status);

//    Page<ReportDO> findByStatusAndAuditTypeAndReceiveUserStatusNotOrderByIdDesc(Pageable pageable, String status, String auditType, String userStatus);

    Page<?  extends ReportModel> findByStatusAndAuditTypeAndHasReviewFalseOrderByIdDesc(Pageable pageable, String status, String auditType);

    Page<?  extends ReportModel> findByStatusNotInAndHasReviewFalseOrderByIdDesc(Pageable pageable, List<String> status);

    Page<?  extends ReportModel> findByStatusOrderByIdDesc(Pageable pageable, String status);

    //查询用户其他为审核中的状态
    List<?  extends ReportModel> findByContentUserIdAndStatusIn(Integer userId, List<String> status);

    ReportModel savePut(ReportModel reportModel);

    Optional<?  extends ReportModel> findById(Integer reportId);

    List<?  extends ReportModel > savePutAll(List<?  extends ReportModel> reportModels);

}
