package com.socialuni.center.web.config;

import java.util.Arrays;
import java.util.List;

public class WhiteUrlListConfig {
    static final List<String> AppWhiteUrlList = Arrays.asList(
            ("/"),
            ("test"),
            //初始化
            ("getAppLaunchData"),
            //初始化的请求
            ("report/queryReportTypes"),
            //查询首页轮播图
            ("queryHomeSwipers"),
            //同步生产环境数据到开发
            ("queryDevAccount"),
            ("queryHotCircles"),
            ("queryCircleTypes"),
            ("admin/syncProdDevAccount"),
            //mock授权登录
            ("mockOAuthUserInfo"),
            ("mockOAuthUserPhoneNum"),
            ("tag/queryTags"),
            ("tag/queryHotTags"),
            ("tag/queryTagTypes"),
            ("tag/queryHotTagType"),
            ("/queryDistricts"),
            ("/queryHotDistricts"),
            ("/devUser/queryDevUserDetail"),
            ("/app/sendErrorLog"),
            ("/app/getAppConfig"),
            //渠道登录的方法
            ("queryProvinceDistricts"),
            ("queryHotProvinceDistricts"),
            ("queryOtherHomeTypeTalks"),
            ("readChat"),
            ("queryChats"),
            ("refreshKeywords"),
            ("refreshConfigMap"),
            ("refreshRedis"),
            ("qqPayNotify"),
            ("wxPayNotify"),
            ("app/checkUpdate"),
            ("match/queryMatchUsers"),
            ("authentication"),
            ("match/queryUsers"),
            ("talk/queryTalks"),
            ("message/queryMessages"),
            ("/webSocketServer"),
            ("/myHandler"),
            ("/queryUserTalks"),
            ("/queryTalkDetail"),
            ("/queryUserDetail"),
            ("/queryAppInitData"),
            ("/queryAppInitDataLoad"),
            ("/queryAppInitDataLoad1"),
            ("/queryAppInitDataReady"),
            ("/img"),
            ("/css")
    );

    static final List<String> needDevIdUrlList = Arrays.asList(
            ("user/providerLogin"),
            ("user/platformLogin"),
            ("user/miniAppLogin"),
            ("user/appLogin"),
            ("appLogin"),
            ("wxLogin"),
            ("bindWxPhoneNum"),
            ("bindPhoneNum"),
            ("sendAuthCode"),
            ("login"),
            ("Login"),
            ("location"),
            ("oauth")
    );
}