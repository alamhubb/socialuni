package com.socialuni.social.content.logic.service;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.content.dao.store.AppConfigStatic;
import com.socialuni.social.content.dao.DO.IllegalWordDO;
import com.socialuni.social.content.dao.DO.KeywordsDO;
import com.socialuni.social.content.dao.repository.IllegalWordRepository;
import com.socialuni.social.content.dao.repository.KeywordsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-03-17 16:36
 */
@Service
public class ViolationKeywordsService {
    @Resource
    private IllegalWordRepository illegalWordRepository;

    @Resource
    private KeywordsRepository keywordsRepository;

    public void refreshKeywords() {
        List<IllegalWordDO> illegalWordDOS = illegalWordRepository.findAllByStatus(SocialuniCommonStatus.enable);
        AppConfigStatic.setIllegals(illegalWordDOS);

        List<KeywordsDO> keywordsDOS= keywordsRepository.findAllByStatus(SocialuniCommonStatus.enable);
        AppConfigStatic.setKeywordDOs(keywordsDOS);
        //目前不需要全部检索然后删除了，已经使用了举报机制
        /*for (String illegal : illegals) {
            if (StringUtils.isNotEmpty(illegal)) {
                String illegalSql = "%" + illegal + "%";
                //系统管理员,告知别人谁发的通知
                UserDO systemUser = UserUtils.getSystemUser();
                //删除所有违规的talk
                List<TalkDO> talkDOS = talkRepository.findAllByContentLikeAndStatus(illegalSql, CommonStatus.enable);
                for (Integer talkIdDO : talkDOS) {
                    Logger.logger.info("违规内容----：" + talkDO.getContent());
                    Logger.logger.info("违规关键词----：" + illegalSql);
                    //删除talk和封禁用户处理
                    violationService.talkViolationHandler(talkDO, ErrorMsg.CONTENT_VIOLATION, true);
                    //给用户发送被封通知
                    //推送消息
                    NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, talkDO.getUser(), talkDO));
                    notifyService.sendNotify(notifyDO);
                }
                //删除所有违规的评论
                List<CommentDO> commentDOS = commentRepository.findAllByContentLikeAndStatus(illegalSql, CommonStatus.enable);
                for (Integer commentIdDO : commentDOS) {
                    Logger.logger.info("违规内容----：" + commentDO.getContent());
                    Logger.logger.info("违规关键词----：" + illegalSql);
                    //删除talk和封禁用户处理
                    violationService.commentViolationHandler(commentDO, ErrorMsg.CONTENT_VIOLATION, true);
                    //给用户发送被封通知
                    NotifyDO notifyDO = notifyRepository.save(new NotifyDO(systemUser, commentDO.getUser(), commentDO, NotifyType.delete_comment));
                    notifyService.sendNotify(notifyDO);
                }
            }
        }*/
    }
}
