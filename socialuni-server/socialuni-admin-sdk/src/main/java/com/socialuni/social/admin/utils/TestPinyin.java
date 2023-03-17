package com.socialuni.social.admin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TestPinyin {
    public static void main(String[] args) {
        //发布的内容，转为拼音，关键词转为拼音，去除空格，去除各种符号，只保留汉字英文，
        //关键词也转为拼音
        String str = "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
        //1 去除特殊字符和空格
        //2 转为拼音
        //3 遍历关键词，看是否包含关键词
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`_~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim().replaceAll(" ", "");
    }
}
