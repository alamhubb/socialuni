package com.socialuni.sdk.service;

import com.socialuni.constant.ReportStatus;
import com.socialuni.entity.model.DO.keywords.KeywordsDO;
import com.socialuni.entity.model.DO.keywords.KeywordsTriggerDetailDO;
import org.springframework.stereotype.Service;

/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 */
@Service
public class KeywordsService {
    //计算关键词违规率，全匹配违规率，变种匹配率
    public void calculateViolateRatioByReportStatus(String auditResult, KeywordsTriggerDetailDO keywordsTriggerDetailDO, KeywordsDO wordDO) {
        //关键词触发总量+1，不确定状态和文本拼音，所以最外层只能触发+1
        Integer totalNum = wordDO.getTotalNum() + 1;
        wordDO.setTotalNum(totalNum);
        //违规
        if (ReportStatus.violation.equals(auditResult)) {
            //关键词违规总量+1
            Integer violateNum = wordDO.getViolateNum() + 1;
            wordDO.setViolateNum(violateNum);
        }else {
            //不违规总量+1
            Integer normalNum = wordDO.getNormalNum() + 1;
            wordDO.setNormalNum(normalNum);
        }
        //违规比和不违规比，统一使用 违规比除总数，1-违规比=不违规比，公式，见底部
        //查看是否为变种匹配
        if (keywordsTriggerDetailDO.getUsePinyin()) {
            //这里变种可以加1
            //误触
            //计算主体误触率，总数+1
            Integer pinyinTotalNum = wordDO.getPinyinTotalNum() + 1;
            wordDO.setPinyinTotalNum(pinyinTotalNum);
            wordDO.setPinyinTotalNum(pinyinTotalNum);
            //违规
            if (ReportStatus.violation.equals(auditResult)) {
                //违规+1
                Integer pinyinViolateNum = wordDO.getPinyinViolateNum() + 1;
                wordDO.setPinyinViolateNum(pinyinViolateNum);
                wordDO.setPinyinViolateNum(pinyinViolateNum);
            } else {
                //误触+1
                Integer pinyinNormalNum = wordDO.getPinyinNormalNum() + 1;
                wordDO.setPinyinNormalNum(pinyinNormalNum);
                wordDO.setPinyinNormalNum(pinyinNormalNum);
            }
            //这里可以计算变种违规率
            //计算主体违规和误触比
            wordDO.setPinyinViolateRatio(wordDO.getPinyinViolateNum().doubleValue() / pinyinTotalNum.doubleValue());
            wordDO.setPinyinViolateRatio(wordDO.getPinyinViolateNum().doubleValue() / pinyinTotalNum.doubleValue());
            wordDO.setPinyinNormalRatio(1 - wordDO.getPinyinViolateRatio());
            wordDO.setPinyinNormalRatio(1 - wordDO.getPinyinViolateRatio());
        } else {
            //这里可以文本触发总数+1
            Integer textTotalNum = wordDO.getTextTotalNum() + 1;
            wordDO.setTextTotalNum(textTotalNum);
            //关键词全匹配
            //违规
            if (ReportStatus.violation.equals(auditResult)) {
                //违规+1
                Integer textViolateNum = wordDO.getTextViolateNum() + 1;
                wordDO.setTextViolateNum(textViolateNum);
            } else {
                //误触
                //误触+1
                Integer TextNormalNum = wordDO.getTextNormalNum() + 1;
                wordDO.setTextNormalNum(TextNormalNum);
            }
            //这里可以计算文本违规率
            //文本违规总数/文本总数
            wordDO.setTextViolateRatio(wordDO.getTextViolateNum().doubleValue() / textTotalNum.doubleValue());
            wordDO.setTextNormalRatio(1 - wordDO.getTextViolateRatio());
        }
        //违规或不违规，已经增加，可以计算违规比例和正常比例了
        //计算关键词总违规比，和误触比
        wordDO.setViolateRatio(wordDO.getViolateNum().doubleValue() / totalNum.doubleValue());
        wordDO.setNormalRatio(1 - wordDO.getViolateRatio());
    }
}
