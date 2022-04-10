package com.socialuni.center.web.temptest;

import com.socialuni.social.sdk.utils.StringUtil;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;

import java.util.regex.Pattern;

public class TestDate {
    public static void main(String[] args) {
        String str = "本人10\n" +
                "想找一个对象\n" +
                "可以聊天\n" +
                "初三\n" +
                "Vx:ljr2157\n" +
                "QQ:656888208\n" +
                "备注:辞妍";
        String reg = "\\d+";//定义正则表达式
        Pattern patten = Pattern.compile(reg);//编译正则表达式
        String ouput = StringUtil.replaceAll(str, patten, (result) -> BirthdayAgeUtil.getAgeBetween10to18Str(result.group()));

        System.out.println(ouput);
       /* List<String> matchStrs = new ArrayList<>();

        String asdf = matcher.replaceAll((result) -> {
            MatchResult result1 = result;
            System.out.println(result.group());
            return result.group();
        })*/
        ;
    }
}
