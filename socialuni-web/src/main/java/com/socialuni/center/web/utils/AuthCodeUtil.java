package com.socialuni.center.web.utils;

import com.socialuni.sdk.utils.IntegerUtils;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 13:48
 */
public class AuthCodeUtil {
    public static String getAuthCode() {
        //验证码长度
        int codeLength = 4;
        //10以下数字
        int codeBound = 10;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(IntegerUtils.random.nextInt(codeBound));
        }
        return code.toString();
    }
    //        Date curDate = new Date();
//        long longtime = curDate.getTime() + CommonConst.month;
        /*double distance = 0.02;
        for (int i = 0; i < 10; i++) {
            distance *= 2;
        }*/
        /*double degrees = 45.0;
        double radians = Math.toRadians(degrees);*/
        /*double dis = 123;
        double r = 6371;
        double lat = 23.155778;
        double haha = 2*Math.asin(
                Math.sin(
                        dis/(2*r)
                )/
                        Math.cos(
                                lat*Math.PI/180
                        )
        );*/
        /*TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(getAuthCode());
        }
        *//*
        Integer totalI = 0;
        Integer crackCount = 100;
        for (int j = 0; j < crackCount; j++) {
            Integer i = 0;
            boolean flag = true;
            while (flag) {
                String autoCode = getAuthCode();
                if (autoCode.equals("1111")) {
                    flag = false;
                }
                i++;
            }
            totalI += i;
        }*/

    // 此处代码为3个数字组成，破解难度太小，顾使用4个数字组成
        /*//验证码由几个相同数字
        IntegercodeCount = 3;
        //数组中存放3个10以下的随机数
        List<Integer> codes = new ArrayList<>();
        for (int i = 0; i < codeCount; i++) {
            codeList.add(StaticObj.random.nextInt(codeBound));
        }
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(StaticObj.random.nextInt(codeCount)).toString());
        }*/
}
