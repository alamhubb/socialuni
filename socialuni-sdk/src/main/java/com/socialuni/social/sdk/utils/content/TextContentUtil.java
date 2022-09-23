package com.socialuni.social.sdk.utils.content;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextContentUtil {
    private static final HashMap<String, Integer> hanziNumberMap = new HashMap<String, Integer>() {{
        put("零", 0);
        put("一", 1);
        put("二", 2);
        put("三", 3);
        put("四", 4);
        put("五", 5);
        put("六", 6);
        put("七", 7);
        put("八", 8);
        put("九", 9);
        put("十", 1);
        put("壹", 1);
        put("贰", 2);
        put("叁", 3);
        put("肆", 4);
        put("伍", 5);
        put("陆", 6);
        put("柒", 7);
        put("捌", 8);
        put("玖", 9);
        put("拾", 1);
        put("①", 1);
        put("②", 2);
        put("③", 3);
        put("④", 4);
        put("⑤", 5);
        put("⑥", 6);
        put("⑦", 7);
        put("⑧", 8);
        put("⑨", 9);
    }};

    // 清除所有空白和特殊字符
    public static String clearAllEmptyAndSpecialChart(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        content = TextContentUtil.clearAllEmptyChart(content);
        content = TextContentUtil.clearAllSpecialChart(content);
        return content;
    }


    // 清除所有空白字符
    private static String clearAllEmptyChart(String content) {
        if (content == null) {
            throw new RuntimeException("要清除空白字符的字符串不能为null");
        }
        content = content.trim().replaceAll("\\s*", "");

        return content;
    }

    // 清除掉所有特殊字符
    private static String clearAllSpecialChart(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        String regEx = "[`_~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        //生成正则
        Pattern p = Pattern.compile(regEx);
        //获取匹配的内容
        Matcher m = p.matcher(content);
        //去除所有空白字符
        return m.replaceAll("");
    }

    //将其他字符的数字转为int数字
    public static String formatOtherNumContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        //匹配非空格内容
        String regEx = "\\S";
        //生成正则
        Pattern p = Pattern.compile(regEx);
        content = TextContentUtil.replaceAll(content, p, (result) -> {
            String resGroup = result.group();
            Integer hanziNum = TextContentUtil.hanziNumberMap.get(resGroup);
            if (hanziNum != null) {
                return hanziNum.toString();
            }
            return resGroup;
        });
        return content;
    }

    //根据方法替换内容
    public static String replaceAll(String content, Pattern pattern,
                                    Function<Matcher, String> replacer) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        Matcher matcher = pattern.matcher(content);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, replacer.apply(matcher));
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
