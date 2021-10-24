/*
package com.socialuni.admin.web.controller.oldadmin;

import com.qingchi.admin.modelvo.ReportVO;
import com.socialuni.social.model.common.ResultRO;
import com.socialuni.social.constant.status.BaseStatus;
import com.qingchi.base.constant.status.ReportStatus;
import com.qingchi.base.mapper.TalkMapper;
import com.socialuni.social.model.common.BaseModelDO;
import com.qingchi.base.model.report.ReportDO;
import com.qingchi.base.model.system.KeywordsDO;
import com.qingchi.base.model.talk.TalkDO;
import com.qingchi.base.repository.keywords.KeywordsRepository;
import com.qingchi.base.repository.report.ReportRepository;
import com.qingchi.base.repository.talk.TalkRepository;
import com.qingchi.base.utils.QingLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 *//*

@RestController
@RequestMapping("keywords")
public class KeywordsTempController {
    @Resource
    private ReportRepository reportRepository;
    @Resource
    private KeywordsRepository keywordsRepository;
    @Resource
    private TalkMapper talkMapper;

    @Resource
    private TalkRepository talkRepository;


    @RequestMapping("test")
    public ResultRO<List<KeywordsDO>> test(@Valid @NotNull String content, @Valid @NotNull Integer count) {
        List<KeywordsDO> list = keywordsRepository.findAll();

//        list.

        return new ResultRO<>(list);
    }

    public ResultRO<List<BaseModelDO>> test5(@Valid @NotNull String content, @Valid @NotNull Integer count) {
//        TencentCloud.textIsViolation(content);
//        Pageable pageable = PageRequest.of(0, count);
//        Page<TalkDO> talkModels = talkRepository.findByStatusNotInAndContentLikeOrderByIdDesc(pageable, ContentStatus.auditStatus, "%" + content + "%");
//        // 数字串集合
//        String numAryReg = "\\d+";
//
//        List<BaseModelDO> list = new ArrayList<>();
////如果字符串长度大于1，如果只有一个5，想不到什么情况只有一个5，所以先忽略这个逻辑
//        for (BaseModelDO modelDO : talkModels.getContent()) {
//            String text = modelDO.getContent();
//            if (StringUtils.isNotEmpty(text)) {
//                List<String> numAry = getMatchers(numAryReg, text);
//                //判断是否包含非5数字
//                //取非判断是否全是5
//                String hasNot5Reg = ".*[^5]+.*";
//                Pattern pattern = Pattern.compile(hasNot5Reg);
//                //是否包含非5
//                for (String text1 : numAry) {
//                    Matcher matcher = pattern.matcher(text1);
//                    boolean all5 = !matcher.matches();
//                    //全是5， 存在拒5，别5，这样的可能
//                    if (all5) {
//                        list.add(modelDO);
//                        break;
//                    }
//                }
//            }
//        }
        return null;
    }


    List<String> getMatchers(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }


    //在所有违规信息中查找高频关键词
    @RequestMapping("keywordsList")
    public ResultRO<KeywordsDO> keywordsList(String content) {
        Pageable pageable = PageRequest.of(0, 200000);
        Page<ReportDO> reportDOPage = reportRepository.findByStatusOrderByIdDesc(pageable, ReportStatus.violation);
        List<ReportDO> queryDos = reportDOPage.getContent();

//        Map<String, Integer> keyMap = KeywordsUtils.chineseWordSegmentationGetKeywordsMap(queryDos);
        Map<String, Integer> keyMap = new HashMap<>();
        Map<String, Integer> sortMap = new HashMap<>();

//        Map<String, Integer> sortMap = KeywordsUtils.sortMapByValue(keyMap);
        */
/*for (Map.Entry<String, Integer> entry : sortMap.entrySet()) {
            if (entry.getValue() > 5) {
                System.out.println(entry.getValue());
            }
//            System.out.println(entry.getValue());
        }*//*

        for (Map.Entry<String, Integer> entry : sortMap.entrySet()) {
            if (entry.getValue() > 5) {
//                System.out.println(entry.getValue());
                System.out.println(entry.getKey());
            }
//            System.out.println(entry.getValue());
        }
        System.out.println(keyMap.size());
        */
/*
        查询违规先关内容
        if (optionalViolateWordDO.isPresent()) {
            KeywordsDO keywordsDO = optionalViolateWordDO.get();
            List<KeywordsTriggerDetailDO> triggerDetailDOS = keywordsTriggerDetailRepository.findTop100ByKeywords(keywordsDO);
            keywordsDO.setTriggers(triggerDetailDOS);
            return new ResultVO<>(keywordsDO);
        }*//*

        return new ResultRO<>();
    }


    @RequestMapping("scanTalkKeywords")
    public ResultRO<List<ReportVO>> scanTalkKeywords() {
        QingLogger.logger.info("结束时间:{}", Long.toString(new Date().getTime() / 1000));
        return new ResultRO<>();
    }

    @RequestMapping("scanCommentKeywords")
    public ResultRO<List<ReportVO>> scanCommentKeywords() {
        QingLogger.logger.info("结束时间:{}", Long.toString(new Date().getTime() / 1000));
        return new ResultRO<>();
    }


    @RequestMapping("scanMessageKeywords")
    public ResultRO<List<ReportVO>> scanMessageKeywords() {
        //先查询出来所有的关键词
        List<KeywordsDO> keywordDOs = keywordsRepository.findAllByStatus(BaseStatus.enable);

        */
/*//*
/获取所有开启的文本违规词
        List<ViolateWordDO> textWordDOs = violateWordRepository.findAllByStatusIsNullAndTextViolateRatioGreaterThanAndOpenTextTrue(0.3);
        //获取所有开启的拼音违规词
        List<ViolateWordDO> pinyinWordDOs = violateWordRepository.findAllByStatusIsNullAndPinyinViolateRatioGreaterThanAndOpenTextTrue(0.2);*//*


        QingLogger.logger.info("开始时间:{}", Long.toString(new Date().getTime() / 1000));
        for (int i = 0; i < 175; i++) {
            QingLogger.logger.info("开始时间i:" + i + ":{}", Long.toString(new Date().getTime() / 1000));
//            Pageable pageable = PageRequest.of(i, 1000);
            List<TalkDO> talkDOS = talkMapper.queryTalksByPageOrderById(i * 1000, 1000);
//            List<TalkDO> talkDOS = talkRepository.findAllByOrderById(pageable);

            QingLogger.logger.info("查询数量:" + talkDOS.size() + "个---------------------");
            if (talkDOS.size() > 0) {
                QingLogger.logger.info("开始id:" + talkDOS.get(0).getId() + "个---------------------");
                QingLogger.logger.info("结束id:" + talkDOS.get(talkDOS.size() - 1).getId() + "个---------------------");
            }
            */
/*List<KeywordsTriggerDO> list = new ArrayList<>();


            int matchContentLength = 6;


            //保存一个原本，存储相关内容，就是本来是污，,.,.的，保存 污.,..的，往后截取6个，或者到最后
            for (TalkDO talkDO : talkDOS) {
                KeywordsTriggerDO keywordsTriggerDO = keywordsService.getKeywordsTriggerDO(talkDO, ContentType.talk);
                list.add(keywordsTriggerDO);
            }*//*



            */
/*Logger.logger.info("保存触发数量:" + list.size() + "个******************");
            keywordsTriggerRepository.saveAll(list);*//*

            QingLogger.logger.info("结束时间I:" + i + ":{}", Long.toString(new Date().getTime() / 1000));
        }
        QingLogger.logger.info("结束时间:{}", Long.toString(new Date().getTime() / 1000));
        return new ResultRO<>();
    }


    // 设置textshow和text格式化等
    */
/*public void forKeywordsPinyin() {
        List<KeywordsDO> list = keywordsRepository.findAll();
        for (KeywordsDO keywordsDO : list) {
            String content = keywordsDO.getTextShow();
            keywordsDO.setText(content.trim().toUpperCase());

            String contentPinyin = Pinyin.toPinyin(content, " ");
            keywordsDO.setPinyinTextShow(contentPinyin);
            keywordsDO.setPinyinText(contentPinyin.replaceAll(" ", "").toUpperCase());
        }
        keywordsRepository.saveAll(list);
    }*//*


}
*/
