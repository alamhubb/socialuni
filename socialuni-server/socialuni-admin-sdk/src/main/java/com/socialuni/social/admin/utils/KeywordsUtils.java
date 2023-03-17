package com.socialuni.social.admin.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordsUtils {

    public static String StringFilter(String str) {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`_~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim().replaceAll(" ", "");

    }


   /* public static Map<String, Integer> chineseWordSegmentationGetKeywordsMap(List<ReportDO> contents) {
        Map<String, Integer> map = new HashMap<>();
        JiebaSegmenter segmenter = new JiebaSegmenter();
        for (ReportDO content : contents) {
            KeywordsUtils.chineseWordSegmentationGetKeywordsMap(content.getReportContent(), map, segmenter);
        }
        return map;
    }

    public static Map<String, Integer> chineseWordSegmentationGetKeywordsMap(String content, Map<String, Integer> keyMap, JiebaSegmenter segmenter) {
        if (content != null) {
            //删除空白字符和空格，去除特殊字符，升为大写
            String text = KeywordsUtils.StringFilter(content.trim().replaceAll("\\s*", ""));
            if (StringUtils.isNotEmpty(text)) {
                text = text.toUpperCase();
//                int textLength = text.length();
                //使用结巴分词
                List<SegToken> segTokenList = segmenter.process(text, JiebaSegmenter.SegMode.INDEX);
                for (SegToken segToken : segTokenList) {
                    String key = segToken.word;
                    if (key.length() > 1) {
                        //向map中求和
                        keyMap.merge(segToken.word, 1, Integer::sum);
                    }
                }
                //根据文本长度遍历，从第一个字开始遍历
                *//*for (int i = 0; i < textLength; i++) {
                    //最多6个字，0 - 5+2
                    int keyMaxCount = 5;
                    //最少两个字
                    for (int j = 0; j < keyMaxCount; j++) {
                        int endIndex = i + 2 + j;
                        //如果结束索引大于文本索引，则退出
                        if (endIndex > textLength) {
                            break;
                        }
                        //获取本次文本
                        String key = text.substring(i, endIndex);
                        //向map中求和
                        keyMap.merge(key, 1, Integer::sum);
                    }
                }*//*
            }
        }
        return keyMap;
    }
*/

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(
                oriMap.entrySet());
        entryList.sort(new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me1.getValue().compareTo(me2.getValue());
        }
    }
}
