package com.socialuni.center.web.temptest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDate {
    public static void main(String[] args) {
        String str = "本人16\n" +
                "想找一个对象\n" +
                "可以聊天\n" +
                "初三\n" +
                "Vx:ljr2157\n" +
                "QQ:656888208\n" +
                "备注:辞妍";
        String reg = "\\d+";//定义正则表达式
        Pattern patten = Pattern.compile(reg);//编译正则表达式
        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
        List<String> matchStrs = new ArrayList<>();

        while (matcher.find()) { //此处find()每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group());//获取当前匹配的值
            String numStr = matcher.group();
            if (numStr.length() < 3) {
                int age = Integer.parseInt(numStr);
                if (age < 18) {
                    System.out.println("包含小于18岁内容");
                }
            }
        }
        for (int i = 0; i < matchStrs.size(); i++) {
            System.out.println(matchStrs.get(i));
        }
    }
}
