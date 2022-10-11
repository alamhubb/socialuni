package com.socialuni.social.report.dev.repository;

import com.socialuni.social.report.dev.entity.ReportDO;
import com.socialuni.social.report.sdk.api.ReportApi;
import com.socialuni.social.report.sdk.model.ReportModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface ReportRepository extends ReportApi,JpaRepository<ReportDO, Integer> {
    ReportDO findOneByContentId(Integer contentId);
    default List<?  extends ReportModel> savePutAll(List<?  extends ReportModel> reportModels){
        for (ReportModel reportModel : reportModels) {
            this.save(reportModel);
        }
        return reportModels;
    }
//    Optional<ReportDO> findFirstOneByTalkId(Integer talkId);

//    Optional<ReportDO> findFirstOneByCommentId(Integer commentId);

//    Optional<ReportDO> findFirstOneByUserImgId(Integer userImgId);

//    Optional<ReportDO> findFirstOneByMessageId(Integer messageId);

//    List<ReportDO> findTop5ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

    List<ReportDO> findTop10ByStatusInOrderByCreateTimeAsc(List<String> status);

    List<ReportDO> findTop10ByContentUserIdOrderByCreateTimeDesc(Integer userId);
    List<ReportDO> findTop20ByStatusInOrderByCreateTimeAsc(List<String> status);
    List<ReportDO> findTop20ByStatusInAndDevIdOrderByCreateTimeAsc(List<String> status, Integer devId);

//    List<ReportDO> findTop20ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

//    List<ReportDO> findTop30ByStatusAndTalkStatusInOrStatusAndUserImgStatusInOrStatusAndCommentStatusInOrStatusAndMessageStatusInOrderByCreateTimeAsc(String status, List<String> talkStatus, String status1, List<String> userImgStatus, String status2, List<String> commentStatus, String status3, List<String> messageStatus);

//    List<ReportDO> findTop10ByReceiveUserIdAndStatusOrderByUpdateTimeDesc(Integer userId, String status);

//    Page<ReportDO> findByStatusAndAuditTypeAndReceiveUserStatusNotOrderByIdDesc(Pageable pageable, String status, String auditType, String userStatus);

    Page<ReportDO> findByStatusAndAuditTypeAndHasReviewFalseOrderByIdDesc(Pageable pageable, String status, String auditType);

    Page<ReportDO> findByStatusNotInAndHasReviewFalseOrderByIdDesc(Pageable pageable, List<String> status);

    Page<ReportDO> findByStatusOrderByIdDesc(Pageable pageable, String status);

    //查询用户其他为审核中的状态
    List<ReportDO> findByContentUserIdAndStatusIn(Integer userId, List<String> status);

}
