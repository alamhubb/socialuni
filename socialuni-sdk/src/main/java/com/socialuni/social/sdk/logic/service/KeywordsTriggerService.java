package com.socialuni.social.sdk.logic.service;

import com.github.promeg.pinyinhelper.Pinyin;
import com.socialuni.social.sdk.constant.CustomWordsRuleType;
import com.socialuni.social.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.social.sdk.dao.repository.BlackKeywordsRepository;
import com.socialuni.social.sdk.dao.repository.CustomKeywordsRepository;
import com.socialuni.social.sdk.dao.repository.WhiteKeywordsRepository;
import com.socialuni.social.sdk.utils.content.TextContentUtil;
import com.socialuni.social.sdk.constant.socialuni.CommonStatus;
import com.socialuni.social.sdk.dao.DO.keywords.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qinkaiyuan
 * @date 2020-03-15 22:05
 */
@Service
public class KeywordsTriggerService {
    @Resource
    private KeywordsService keywordsService;

/*
    public List<KeywordsTriggerDetailDO> checkContentTriggerKeywords(
            BaseModelDO baseModelDO,
            String contentType,
            List<KeywordsCopyDO> keywordDOs
    ) {
        boolean isTest = false;
        List<KeywordsTriggerDetailDO> keywordsTriggers = new ArrayList<>();
        int matchContentLength = 6;
        boolean modifyReportStatusFlag = false;

        String baseModelContent = baseModelDO.getContent();
        Integer baseModelId = baseModelDO.getDbId();

        //记录这条内容是否有触发关键词
        //记录触发的关键词列表
        //获取文本主要主体
        //不为null
        if (baseModelContent != null) {
            //删除空白字符和空格，去除特殊字符，升为大写
            String contentFormat = KeywordsUtils.StringFilter(baseModelContent.trim().replaceAll("\\s*", "").toUpperCase());
            //文本不为空,拼音和文本都需要判空
            if (StringUtils.isNotEmpty(contentFormat)) {
                //以下逻辑，为获取每个字在拼音中的位置， 拼音与文字位置对应，做的辅助功能

                //转为拼音先有分割，记录位置使用
                String contentPinyinHasEmpty = Pinyin.toPinyin(contentFormat, " ");
                //替换分割，改为无分割，并转为大写
                String contentPinyin = contentPinyinHasEmpty.replaceAll(" ", "").toUpperCase();
                //一组拼音对应一个字
                String[] contentPinyinAry = contentPinyinHasEmpty.split(" ");
                //存储字体索引对应的 拼音字数结束位置
                List<Integer> contentWordIndexList = new ArrayList<>();
                contentWordIndexList.add(0);
                int sum = 0;
                //遍历拼音数组，得到每个字在拼音中的位置
                for (String s : contentPinyinAry) {
                    //位置叠加
                    sum += s.length();
                    //记录自己这个字在拼音中的结束位置
                    contentWordIndexList.add(sum);
                }
                //遍历关键词
                for (KeywordsCopyDO keywordsDO : keywordDOs) {
                    //这部分可以优化，不为文字以后才弄拼音
                    String keywordsText = keywordsDO.getText();
                    //判空
                    if (StringUtils.isNotEmpty(keywordsText)) {
                        //记录这个关键词是否触发
                        KeywordsTriggerDetailDO keywordsTriggerDetailDO = null;

                        //如果打开了关键词打开了文本匹配，且文本包含关键词主体
                        if (keywordsDO.getOpenText() && contentFormat.contains(keywordsText)) {
                            //截取主要违规内容
                            int subStartIndex = contentFormat.indexOf(keywordsText);

                            //截取主要违规内容
                            String matchText = StringUtils.substring(contentFormat, subStartIndex, subStartIndex + matchContentLength);

                            //如果文本不违规次数小于20，或者 违规率大于0.3
                            if (keywordsDO.getTextNormalNum() < 20 || keywordsDO.getTextViolateRatio() > 0.2 || isTest) {
                                modifyReportStatusFlag = true;
                                //reportstatus因为会有两种状态初始审查，是初始，预审核，
                                keywordsTriggerDetailDO = new KeywordsTriggerDetailDO(
                                        baseModelContent,
                                        baseModelId,
                                        contentType,
                                        keywordsDO.getId(),
                                        keywordsDO.getTextShow(),
                                        matchText,
                                        false
                                );
                            }
                            //如果打开了变种匹配
                        } else if (keywordsDO.getOpenPinyin()) {
//                            keywordsTriggerFlag = isKeywordsTriggerFlag(matchContentLength, contentFormat, contentPinyin, contentWordIndexList, wordDO, keywordsTriggerDetailDO);
                            //如果文本不违规次数小于20，或者 违规率大于0.2
                            if (keywordsDO.getPinyinNormalNum() < 20 || keywordsDO.getPinyinViolateRatio() > 0.2 || isTest) {
                                modifyReportStatusFlag = true;
                                //得到拼音的变种
                                keywordsTriggerDetailDO = getKeywordsTriggerDetailDO(baseModelContent, baseModelId, contentType, matchContentLength, contentFormat, contentPinyin, contentWordIndexList, keywordsDO);
                            }
                        }

                        //如果触发了这个关键词，则将这个关键词添加到触发详情列表中
                        if (keywordsTriggerDetailDO != null) {
                            keywordsTriggers.add(keywordsTriggerDetailDO);
                        }
                    }
                }
            }
        }
        return keywordsTriggers;
    }
*/

    public List<KeywordsTriggerDetailDO> checkContentTriggerKeywords(
            SocialUnionContentBaseDO contentBO,
            List<KeywordsDO> keywordDOs,
            boolean isTest
    ) {
        String baseModelContent = contentBO.getContent();
        List<KeywordsTriggerDetailDO> keywordsTriggers = new ArrayList<>();
        if (StringUtils.isEmpty(baseModelContent)) {
            return keywordsTriggers;
        }

        int matchContentLength = 6;
        boolean modifyReportStatusFlag = false;

        //记录这条内容是否有触发关键词
        //记录触发的关键词列表
        //获取文本主要主体
        //不为null
        //删除空白字符和空格，去除特殊字符，升为大写
        String contentFormat = TextContentUtil.clearAllEmptyAndSpecialChart(baseModelContent).toUpperCase();
        //文本不为空,拼音和文本都需要判空
        if (StringUtils.isNotEmpty(contentFormat)) {
            //以下逻辑，为获取每个字在拼音中的位置， 拼音与文字位置对应，做的辅助功能

            //转为拼音先有分割，记录位置使用
            String contentPinyinHasEmpty = Pinyin.toPinyin(contentFormat, " ");
            //替换分割，改为无分割，并转为大写
            String contentPinyin = contentPinyinHasEmpty.replaceAll(" ", "").toUpperCase();
            //一组拼音对应一个字
            String[] contentPinyinAry = contentPinyinHasEmpty.split(" ");
            //存储字体索引对应的 拼音字数结束位置
            List<Integer> contentWordIndexList = new ArrayList<>();
            contentWordIndexList.add(0);
            int sum = 0;
            //遍历拼音数组，得到每个字在拼音中的位置
            for (String s : contentPinyinAry) {
                //位置叠加
                sum += s.length();
                //记录自己这个字在拼音中的结束位置
                contentWordIndexList.add(sum);
            }
            //遍历关键词
            for (KeywordsDO keywordsDO : keywordDOs) {
                //这部分可以优化，不为文字以后才弄拼音，本来已经特殊处理了，可是因为有5和圈5重复，导致需要给5加个，
                String keywordsText = TextContentUtil.clearAllEmptyAndSpecialChart(keywordsDO.getText()).toUpperCase();
                //判空
                if (StringUtils.isNotEmpty(keywordsText)) {
                    //记录这个关键词是否触发
                    KeywordsTriggerDetailDO keywordsTriggerDetailDO = null;

                    //如果打开了关键词打开了文本匹配，且文本包含关键词主体
                    if (keywordsDO.getOpenText() && contentFormat.contains(keywordsText)) {
                        //如果关键词是5，特殊处理
                        //这段逻辑主要是，
                        //满足黑名单直接为违规
                        //满足白名单直接不违规
                        //如果不是全为5的数字组合，不违规，跳出
                        if (checkCustomKeywords(contentFormat, keywordsText)) continue;
                        //截取主要违规内容
                        int subStartIndex = contentFormat.indexOf(keywordsText);

                        //截取主要违规内容
                        String matchText = StringUtils.substring(contentFormat, subStartIndex, subStartIndex + matchContentLength);

                        //如果文本不违规次数小于20，或者 违规率大于0.3
                        if (keywordsDO.getTextNormalNum() < 20 || keywordsDO.getTextViolateRatio() > 0.2 || isTest) {
                            modifyReportStatusFlag = true;
                            //reportstatus因为会有两种状态初始审查，是初始，预审核，
                            keywordsTriggerDetailDO = new KeywordsTriggerDetailDO(
                                    contentBO.getContent(),
                                    contentBO.getUnionId(),
                                    contentBO.getContentType(),
                                    keywordsDO.getId(),
                                    keywordsDO.getTextShow(),
                                    matchText,
                                    false
                            );
                        }
                        //如果打开了变种匹配
                    } else if (keywordsDO.getOpenPinyin()) {
//                            keywordsTriggerFlag = isKeywordsTriggerFlag(matchContentLength, contentFormat, contentPinyin, contentWordIndexList, wordDO, keywordsTriggerDetailDO);
                        //如果文本不违规次数小于20，或者 违规率大于0.2
                        if (keywordsDO.getPinyinNormalNum() < 20 || keywordsDO.getPinyinViolateRatio() > 0.2 || isTest) {
                            modifyReportStatusFlag = true;
                            //得到拼音的变种
                            keywordsTriggerDetailDO = getKeywordsTriggerDetailDO(contentBO.getContent(),
                                    contentBO.getUnionId(),
                                    contentBO.getContentType(), matchContentLength, contentFormat, contentPinyin, contentWordIndexList, keywordsDO);
                        }
                    }

                    //如果触发了这个关键词，则将这个关键词添加到触发详情列表中
                    if (keywordsTriggerDetailDO != null) {
                        keywordsTriggers.add(keywordsTriggerDetailDO);
                    }
                }
            }
        }
        return keywordsTriggers;
    }

    @Resource
    CustomKeywordsRepository customKeywordsRepository;
    @Resource
    BlackKeywordsRepository blackKeywordsRepository;
    @Resource
    WhiteKeywordsRepository whiteKeywordsRepository;

    //是否包含违规自定义关键词
    private boolean checkCustomKeywords(String contentFormat, String keywordsText) {
        List<CustomKeywordsDO> customKeywordsDOS = customKeywordsRepository.findAllByStatus(CommonStatus.enable);
        for (CustomKeywordsDO customKeywordsDO : customKeywordsDOS) {
            //转大写
            String customKeywords = customKeywordsDO.getWord().toUpperCase();
            //5,wu,w
            if (keywordsText.equals(customKeywords)) {
                //黑名单
                //一层，判断为违规， 不5的，拒污的 5不5 不5的 不拒5 特别5 不拒绝5
                //白名单
                //二层不违规 不5，拒5，别5, 5别，绝5, 5的别，不要5 5🉐别，5的别，5得别, 5kg, 5斤，5岁，5公斤

                //不{0}的
                List<BlackKeywordsDO> blackKeywordsDOS = blackKeywordsRepository.findAllByStatus(CommonStatus.enable);
//                List<String> blacklist = Arrays.asList("不5的", "拒5的", "5不5", "不拒5", "特别5", "不拒绝{0}");

                boolean isViolate = false;

                //一层 包含黑名单违规
                for (BlackKeywordsDO blackKeywordsDO : blackKeywordsDOS) {
                    //包含黑名单违规, 黑名单+自定义，组合出关键词组合
                    if (contentFormat.contains(MessageFormat.format(blackKeywordsDO.getWord(), customKeywords))) {
                        //设置为违规
                        isViolate = true;
                        blackKeywordsDO.setTriggerCount(blackKeywordsDO.getTriggerCount() + 1);
                        blackKeywordsRepository.save(blackKeywordsDO);
                        break;
                    }

                }
                /*for (String s : blacklist) {
                    //包含黑名单违规
                    if (contentFormat.contains(s)) {
                        //设置为违规
                        isViolate = true;
                        break;
                    }
                }*/

                List<String> whitelist = Arrays.asList("不{0}", "拒{0}", "别{0}", "绝{0}", "{0}的别", "不要{0}", "{0}🉐别", "{0}的别"
                        , "{0}得别", "{0}kg", "{0}斤", "{0}岁", "{0}公斤");

                List<WhiteKeywordsDO> whiteKeywordsDOS = whiteKeywordsRepository.findAllByStatus(CommonStatus.enable);
                //不违规
                if (!isViolate) {
                    isViolate = true;
                    //二层 包含白名单不违规
                    for (WhiteKeywordsDO whiteKeywordsDO : whiteKeywordsDOS) {
                        //包含白名单不违规
                        if (contentFormat.contains(MessageFormat.format(whiteKeywordsDO.getWord(), customKeywords))) {
                            //设置为不违规
                            isViolate = false;
                            whiteKeywordsDO.setTriggerCount(whiteKeywordsDO.getTriggerCount() + 1);
                            whiteKeywordsRepository.save(whiteKeywordsDO);
                            break;
                        }
                    }

                    //违规，不符合白名单才执行
                    if (isViolate) {
                        isViolate = false;
                        //字母规则
                        String numAryReg = "\\w+";
                        if (CustomWordsRuleType.num.equals(customKeywordsDO.getRuleType())) {
                            // 数字串集合，获取数字集合
                            numAryReg = "\\d+";
                        }
                        //如果字符串长度大于1，如果只有一个5，想不到什么情况只有一个5，所以先忽略这个逻辑
                        List<String> numAry = getMatchers(numAryReg, contentFormat);
                        //判断是否包含非5数字
                        //取非判断是否全是5
                        String hasNot5Reg = ".*[^" + customKeywords + "]+.*";
                        Pattern pattern = Pattern.compile(hasNot5Reg);
                        //是否包含非5
                        for (String text1 : numAry) {
                            Matcher matcher = pattern.matcher(text1);
                            boolean all5 = !matcher.matches();
                            //全是5， 违规
                            if (all5) {
                                customKeywordsDO.setTriggerCount(customKeywordsDO.getTriggerCount() + 1);
                                customKeywordsRepository.save(customKeywordsDO);
                                isViolate = true;
                                break;
                            }
                        }
                        //如果不包含全是5，则不违规，则进入下次
                        if (!isViolate) {
                            return true;
                        }
                    } else {
                        //不违规，不往下走，执行下次循环
                        return true;
                    }
                }
                //违规
            }
        }
        return false;
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

    //变种匹配构建
    private KeywordsTriggerDetailDO getKeywordsTriggerDetailDO(
            String baseModelContent,
            Integer baseModelId,
            String contentType,
            int matchContentLength,
            String contentFormat,
            String contentPinyin,
            List<Integer> contentWordIndexList,
            KeywordsDO wordDO
    ) {
        String keywordsPinyin = wordDO.getPinyinText();

        KeywordsTriggerDetailDO keywordsTriggerDetailDO = null;
        //如果变种文本，包含变种关键词
        if (contentPinyin.contains(keywordsPinyin)) {
            //修改标识为触发
            //获得变种关键词的位置
            int subStartPinyinIndex = contentPinyin.indexOf(keywordsPinyin);

            int subStartIndex = 0;
            for (int j = 0; j < contentWordIndexList.size(); j++) {
                Integer strIndex = contentWordIndexList.get(j);
                //变种关键词位置，大于等于字体位置时，则为这个字体
                if (strIndex > subStartPinyinIndex) {
                    subStartIndex = j - 1;
                    break;
                }
            }

            //截取主要违规内容
            String matchText = StringUtils.substring(contentFormat, subStartIndex, subStartIndex + matchContentLength);

            //存储主要变种内容
            String matchPinyin = StringUtils.substring(contentPinyin, subStartPinyinIndex, subStartPinyinIndex + matchContentLength * 3);

            //变种匹配构建
            keywordsTriggerDetailDO = new KeywordsTriggerDetailDO(
                    baseModelContent,
                    baseModelId,
                    contentType,
                    wordDO.getId(),
                    wordDO.getTextShow(),
                    matchText,
                    wordDO.getPinyinText(),
                    matchPinyin
            );
        }
        return keywordsTriggerDetailDO;
    }

}

/*
    //变种匹配构建
    private KeywordsTriggerDetailDO getKeywordsTriggerDetailDO(
            String baseModelContent,
            Integer baseModelId,
            String contentType,
            int matchContentLength,
            String contentFormat,
            String contentPinyin,
            List<Integer> contentWordIndexList,
            KeywordsCopyDO wordDO
    ) {
        String keywordsPinyin = wordDO.getPinyinText();

        KeywordsTriggerDetailDO keywordsTriggerDetailDO = null;
        //如果变种文本，包含变种关键词
        if (contentPinyin.contains(keywordsPinyin)) {
            //修改标识为触发
            //获得变种关键词的位置
            int subStartPinyinIndex = contentPinyin.indexOf(keywordsPinyin);

            int subStartIndex = 0;
            for (int j = 0; j < contentWordIndexList.size(); j++) {
                Integer strIndex = contentWordIndexList.get(j);
                //变种关键词位置，大于等于字体位置时，则为这个字体
                if (strIndex > subStartPinyinIndex) {
                    subStartIndex = j - 1;
                    break;
                }
            }

            //截取主要违规内容
            String matchText = StringUtils.substring(contentFormat, subStartIndex, subStartIndex + matchContentLength);

            //存储主要变种内容
            String matchPinyin = StringUtils.substring(contentPinyin, subStartPinyinIndex, subStartPinyinIndex + matchContentLength * 3);

            //变种匹配构建
            keywordsTriggerDetailDO = new KeywordsTriggerDetailDO(
                    baseModelContent,
                    baseModelId,
                    contentType,
                    wordDO.getId(),
                    wordDO.getTextShow(),
                    matchText,
                    wordDO.getPinyinText(),
                    matchPinyin
            );
        }
        return keywordsTriggerDetailDO;
    }
*/

