/*
package com.socialuni.admin.web.controller.oldadmin;


import com.socialuni.admin.web.service.ViolationService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.entity.model.DO.keywords.KeywordsDO;
import com.socialuni.social.sdk.constant.status.ConstBoolean;
import com.socialuni.social.sdk.mapper.TalkMapper;
import com.socialuni.social.sdk.repository.*;
import com.socialuni.social.sdk.store.TalkQueryStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 *//*

@RestController
@RequestMapping("keywords")
public class KeywordsManageController {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private ViolationService violationService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private NotifyService notifyService;
    @Resource
    private KeywordsRepository keywordsRepository;
    @Resource
    private KeywordsTriggerDetailRepository keywordsTriggerDetailRepository;

    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkQueryStore talkQueryStore;
    @Resource
    private TalkMapper talkMapper;
    @Resource
    private CommentMapper commentMapper;


    @PostMapping("addKeywords")
    public ResultRO<KeywordsDO> addKeywords(@Valid @NotNull String content, String cause) {
        content = content.trim();
        if (StringUtils.isEmpty(content)) {
            return new ResultRO<>("不能为空");
        }
        Optional<KeywordsDO> optionalViolateWordDO = keywordsRepository.findTopOneByText(content);
        if (optionalViolateWordDO.isPresent()) {
            return new ResultRO<>("不能为重复");
        }
        KeywordsDO keywordsDO = new KeywordsDO(content, cause);

        keywordsRepository.save(keywordsDO);
        return new ResultRO<>();
    }

    @PostMapping("closeKeywords")
    public ResultRO<KeywordsDO> closeKeywords(Integer id, String closeCause) {
        //获取关键词
        Optional<KeywordsDO> optionalViolateWordDO = keywordsRepository.findById(id);
        //存在
        if (!optionalViolateWordDO.isPresent()) {
            return new ResultRO<>("错误了");
        }

        KeywordsDO keywordsDO = optionalViolateWordDO.get();
        keywordsDO = getKeywordsDOResultVO(keywordsDO, closeCause);
        keywordsRepository.save(keywordsDO);
        return new ResultRO<>();
    }

    private KeywordsDO getKeywordsDOResultVO(KeywordsDO keywordsDO, String closeCause) {

        keywordsDO.setStatus(BaseStatus.delete);
        keywordsDO.setOpenPinyin(ConstBoolean.close);
        keywordsDO.setOpenText(ConstBoolean.close);

        keywordsDO.setDeleteCause(closeCause);
        keywordsDO.setUpdateTime(new Date());
        return keywordsDO;
    }


    @PostMapping("openPinyinOrText")
    public ResultRO<KeywordsDO> openPinyinOrText(Integer id, String type, String cause) {
        //获取关键词
        Optional<KeywordsDO> optionalViolateWordDO = keywordsRepository.findById(id);
        //存在
        if (!optionalViolateWordDO.isPresent()) {
            return new ResultRO<>("错误了");
        }

        KeywordsDO keywordsDO = getKeywordsDOResultVO(optionalViolateWordDO.get(), type, cause);
        keywordsRepository.save(keywordsDO);
        return new ResultRO<>();
    }

//    @GetMapping("batchClosePinyinOrTexts"),批量关闭时使用
    public ResultRO<KeywordsDO> batchOpenPinyinOrTexts() {
        //获取关键词
        List<KeywordsDO> optionalViolateWordDOs = keywordsRepository.findAllByStatusAndOpenPinyinIsTrueAndPinyinNormalNumGreaterThanAndPinyinViolateRatioLessThan(StatusConst.enable, 19, 0.4);

        for (KeywordsDO keywordsDO : optionalViolateWordDOs) {
            getKeywordsDOResultVO(keywordsDO, "pinyin", "批量关闭");
        }

        keywordsRepository.saveAll(optionalViolateWordDOs);
        return new ResultRO<>();
    }

    private KeywordsDO getKeywordsDOResultVO(KeywordsDO keywordsDO, String type, String cause) {
        //取反
        if (type.equals("text")) {
            keywordsDO.setOpenText(!keywordsDO.getOpenText());
            //开启
            if (keywordsDO.getOpenText()) {
                //设置重新开启原因
                keywordsDO.setReopenTextCause(cause);
            } else {
                //关闭
                keywordsDO.setCloseTextCause(cause);
            }
        } else {
            //取反开启关闭
            keywordsDO.setOpenPinyin(!keywordsDO.getOpenPinyin());
            //开启
            if (keywordsDO.getOpenPinyin()) {
                //设置重新开启原因
                keywordsDO.setReopenPinyinCause(cause);
            } else {
                //关闭
                //设置关闭原因
                keywordsDO.setClosePinyinCause(cause);
            }
        }

        //先执行操作，然后统一判断处理
        if (keywordsDO.getOpenText() && keywordsDO.getOpenPinyin()) {
            //设置总数为，两者相加
            keywordsDO.setViolateNum(keywordsDO.getTextViolateNum() + keywordsDO.getPinyinViolateNum());
            keywordsDO.setNormalNum(keywordsDO.getTextNormalNum() + keywordsDO.getPinyinNormalNum());
            keywordsDO.setTotalNum(keywordsDO.getViolateNum() + keywordsDO.getNormalNum());

            //设置总体的比率
            keywordsDO.setViolateRatio(keywordsDO.getViolateNum().doubleValue() / keywordsDO.getTotalNum().doubleValue());
            keywordsDO.setNormalRatio(1 - keywordsDO.getViolateRatio());
        } else if (keywordsDO.getOpenText()) {
            //总体等于text
            keywordsDO.setViolateNum(keywordsDO.getTextViolateNum());
            keywordsDO.setNormalNum(keywordsDO.getTextNormalNum());
            keywordsDO.setTotalNum(keywordsDO.getTextTotalNum());
            //设置总体的比率，直接等于文本的了
            keywordsDO.setViolateRatio(keywordsDO.getTextViolateRatio());
            keywordsDO.setNormalRatio(keywordsDO.getTextNormalRatio());
        } else if (keywordsDO.getOpenPinyin()) {
            //关闭文本的话，违规等于拼音的
            keywordsDO.setViolateNum(keywordsDO.getPinyinViolateNum());
            keywordsDO.setNormalNum(keywordsDO.getPinyinNormalNum());
            keywordsDO.setTotalNum(keywordsDO.getPinyinTotalNum());
            //设置总体的比率，直接等于变种的了
            keywordsDO.setViolateRatio(keywordsDO.getPinyinViolateRatio());
            keywordsDO.setNormalRatio(keywordsDO.getPinyinNormalRatio());
        } else {
            return getKeywordsDOResultVO(keywordsDO, cause);
        }
        keywordsDO.setUpdateTime(new Date());
        return keywordsDO;
    }

}
*/
