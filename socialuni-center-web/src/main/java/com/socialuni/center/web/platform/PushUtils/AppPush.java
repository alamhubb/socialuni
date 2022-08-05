package com.socialuni.center.web.platform.PushUtils;

import java.io.IOException;

public class AppPush {

    // STEP1：获取应用基本信息
    private static String appId = "Wi6p610tFa7MIXcjjAuFU7";
    private static String appKey = "3FBOjyXBWTAV4TVxivkm27";
    //    private static String appSecret = "enD9EwYqQr7J4qhNFEUz9";
    private static String masterSecret = "zJ2gbckW6fAM3vDo1sE0V4";
    // 如果需要使用HTTPS，直接修改url即可
    //private static String url = "https://api.getui.com/apiex.htm";
    private static String url = "http://api.getui.com/apiex.htm";

    public static void main(String[] args) throws IOException {
        /*IGtPush push = new IGtPush(url, appKey, masterSecret);
        // STEP1：选择模板
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);


        // STEP2：设置通知样式
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 配置自定义铃声，需要在客户端开发时嵌入
        style.setRingName("sound");
        // 角标, 必须大于0, 个推通道下发有效; 此属性目前仅针对华为 EMUI 4.1 及以上设备有效
        style.setBadgeAddNum(1);
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        template.setStyle(style);

//        template.setAPNInfo(getAPNPayload()); //详见本页iOS通知样式设置



        // STEP3：设置推送其他参数
        SingleMessage message = new SingleMessage();
        message.setData(template);
        // 厂商通道下发策略（可选项），参数含义见【推送API】页面
        message.setStrategyJson("{\"default\":4,\"ios\":4,\"st\":4}");
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId("97ec5b2a10117a1970d950603b2d0b67");
        // STEP4：执行推送
        push.pushMessageToSingle(message, target);
*/

        /*IGtPush push = new IGtPush(url, appKey, masterSecret);

        Style0 style = new Style0();
        // STEP2：设置推送标题、推送内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        style.setLogo("push.png");  // 设置推送图标
        // STEP3：设置响铃、震动等推送效果
        style.setRing(true);  // 设置响铃
        style.setVibrate(true);  // 设置震动


        // STEP4：选择通知模板
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);


        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒


        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setOffline(true);
        singleMessage.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒

        Target target = new Target();
        target.setAppId(appId);
        target.setClientId("97ec5b2a10117a1970d950603b2d0b67");
        // STEP6：执行推送
//        IPushResult ret = push.pushMessageToApp(message);
        IPushResult ret = push.pushMessageToSingle(singleMessage, target);
        System.out.println(ret.getResponse().toString());*/
    }
}
