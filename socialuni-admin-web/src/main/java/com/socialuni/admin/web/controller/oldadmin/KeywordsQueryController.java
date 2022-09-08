package com.socialuni.admin.web.controller.oldadmin;

import com.socialuni.admin.web.model.KeywordsDetailVO;
import com.socialuni.admin.web.service.ViolationService;
import com.socialuni.admin.web.utils.CheckIsAdminUtil;
import com.socialuni.sdk.dao.repository.*;
import com.socialuni.sdk.repository.*;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.constant.socialuni.ReportStatus;
import com.socialuni.sdk.model.DO.base.BaseModelDO;
import com.socialuni.sdk.model.DO.comment.SocialCommentDO;
import com.socialuni.sdk.model.DO.keywords.KeywordsDO;
import com.socialuni.sdk.model.DO.keywords.KeywordsTriggerDetailDO;
import com.socialuni.sdk.model.DO.message.MessageDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.mapper.TalkMapper;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.logic.service.KeywordsService;
import com.socialuni.sdk.logic.service.KeywordsTriggerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;


@RestController
@RequestMapping("keywords")
public class KeywordsQueryController {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ViolationService violationService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private KeywordsRepository keywordsRepository;
    @Resource
    private KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;

    @Resource
    private TalkRepository talkRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private TalkMapper talkMapper;

    @Resource
    private KeywordsService keywordsService;

    @Resource
    private KeywordsTriggerService keywordsTriggerService;

    /*
     *查询关键词列表
     *
     *@return*/


    @PostMapping("queryKeywords")
    public ResultRO<List<KeywordsDO>> queryKeywords() {
        CheckIsAdminUtil.checkAdmin();
        //先查询出来所有的关键词
        List<KeywordsDO> wordDOs = keywordsRepository.findAllByStatusOrderByTextViolateRatioDesc(CommonStatus.enable);

        return new ResultRO<>(wordDOs);
    }

    /* * 功能说明
     * 查询单个关键词违规律详细信息
     * <p>
     * 逻辑说明
     * <p>
     * 前台需要展示，已存储的关键词信息
     * 临时生成的关键词信息
     * 违规内容列表
     * 不违规内容列表
     * <p>
     * 根据前台传入数量获取 talk、comment、msg各n条，获取不为待审核和预审核状态的内容，id倒序
     * 遍历content
     * 根据关键词校验，获取触发的详情，主要结果是否变种触发
     * 根据触发结果，修改关键词触发率
     * 返回内容
     *
     * @param content
     * @return*/


    @PostMapping("queryKeyword")
    public ResultRO<KeywordsDetailVO> queryKeyword(@Valid @NotNull String content, @Valid @NotNull Integer count) {
        CheckIsAdminUtil.checkAdmin();
        content = content.trim();
        if (StringUtils.isEmpty(content)) {
            throw new SocialBusinessException("不能为空");
        }
        //获取talk，msg，comment 各 3w条

        //通用逻辑
        KeywordsDO keywordsDO = new KeywordsDO(content, "");
        //得到所有触发的
        List<BaseModelDO> baseModelDOS = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, count);
        Page<SocialTalkDO> talkModels = talkRepository.findByStatusNotInOrderByIdDesc(pageable, ContentStatus.auditStatus);
        Page<SocialCommentDO> commentDOS = commentRepository.findByStatusNotInOrderByIdDesc(pageable, ContentStatus.auditStatus);
        Page<MessageDO> messageDOS = messageRepository.findByStatusNotInOrderByIdDesc(pageable, ContentStatus.auditStatus);

        baseModelDOS.addAll(talkModels.getContent());
        baseModelDOS.addAll(commentDOS.getContent());
        baseModelDOS.addAll(messageDOS.getContent());

        //不违规记录
        List<KeywordsTriggerDetailDO> triggerDetailDOS = new ArrayList<>();
        //违规记录
        List<KeywordsTriggerDetailDO> vioTriggerDetailDOS = new ArrayList<>();
        //进行校验的关键词列表
        List<KeywordsDO> keywordsDOS = Collections.singletonList(keywordsDO);
        //遍历talk
        for (BaseModelDO modelDO : baseModelDOS) {
            String auditResult = modelDO.getStatus();
            // 根据10w条去触发这个keywords
            //然后得到keywordsTriggers
            List<KeywordsTriggerDetailDO> keywordsTriggers = keywordsTriggerService
                    .checkContentTriggerKeywords(modelDO, modelDO.getReportContentType(), keywordsDOS, true);

            for (KeywordsTriggerDetailDO keywordsTrigger : keywordsTriggers) {
                //然后传入keyword，和状态，和keyword
                keywordsService.calculateViolateRatioByReportStatus(auditResult, keywordsTrigger, keywordsDOS.get(0));
            }
            //如果违规，添加到违规中
            if (auditResult.equals(ReportStatus.violation)) {
                vioTriggerDetailDOS.addAll(keywordsTriggers);
                //如果不违规，添加到不违规中
            } else {
                triggerDetailDOS.addAll(keywordsTriggers);
            }
        }

        //生成前台展示需要的
        KeywordsDetailVO keywordsDetailVO = new KeywordsDetailVO();
        //如果数据库存在，则赋值db
        Optional<KeywordsDO> optionalViolateWordDO = keywordsRepository.findTopOneByText(content);
        optionalViolateWordDO.ifPresent(keywordsDetailVO::setKeywords);

        keywordsDetailVO.setTempKeywords(keywordsDO);
        keywordsDetailVO.setTriggerDetails(triggerDetailDOS);
        keywordsDetailVO.setVioTriggerDetails(vioTriggerDetailDOS);

        return new ResultRO<>(keywordsDetailVO);
    }


}
