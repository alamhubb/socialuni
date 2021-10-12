package com.socialuni.social.sdk.utils;

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
}
