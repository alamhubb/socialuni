package com.socialuni.social.im.contrller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReferenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * <a href='https://doc.rentsoft.cn/#/callback/callback'>im的第三方回调说明</a>
 * 用于控制消息的操作。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/30 18:53
 * @since 1.0
 */
@Controller
@RequestMapping("/callback/openIm")
public class OpenImCallbackController {
    public static final Logger logger = LoggerFactory.getLogger(OpenImCallbackController.class);
    @PostMapping
    public ResponseEntity<String> callback(@RequestBody(required = false) String jsonStr) throws IOException {
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //获取私聊单聊指令名称
        String callbackCommand = jsonObject.getStr("callbackCommand");
        //根据指令名称找类
        String className = OpenImCallbackController.class.getName() + "$" + StrUtil.upperFirst(callbackCommand) + "Request";
        Class<Object> loadClass = ClassUtil.loadClass( className, false);
        Object toBean = jsonObject.toBean(loadClass);
        CallbackSuperResponse callbackSuperResponse = null;
        if(toBean instanceof CallbackBaseRequest){
            CallbackBaseRequest callbackBaseRequest = (CallbackBaseRequest) toBean;
            callbackSuperResponse = callbackBaseRequest.handler();
        }
        // 创建响应头对象，并添加头信息。
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        // 返回结果值。
        String okBody = JSONUtil.toJsonStr(callbackSuperResponse);
        return new ResponseEntity<>(okBody == null ? StrUtil.EMPTY_JSON : okBody, headers, HttpStatus.OK);
    }

    public static void main(String[] args) {
        System.out.println(CallbackBaseRequest.class.getName());
        System.out.println(ClassUtil.loadClass("com.socialuni.social.im.contrller.OpenImCallbackController$CallbackBaseRequest"));
    }
    ///////////////////////////////////////////
    ///////              抽象的实体
    /////////////////////////////////////////////
    @Data
    public class CallbackBaseRequest {
        /** 回调指令 */
        String callbackCommand;
        /** 本次操作ID */
        String operationID;

        /**
         * 处理具体的业务。
         * @return
         */
        public CallbackSuperResponse handler(){
            // 设置操作符。
            CallbackSuperResponse callbackSuperResponse = new CallbackSuperResponse();
            callbackSuperResponse.setOperationID(this.operationID);
            return callbackSuperResponse;
        }
    }
    @Data
    public class CallbackSuperRequest extends CallbackBaseRequest{
        /** 发送者ID */
        String sendID;
        /** 服务器生成的消息的唯一ID */
        String serverMsgID;
        /** 客户端生成的消息唯一ID */
        String clientMsgID;

        /** 发送者平台号，模拟用户发送时填写， 1->IOS,2->Android,3->Windows,4->OSX,5->Web,5->MiniWeb,7->Linux */
        Integer senderPlatformID;
    }

    /**
     * ActionCode和ErrCode不影响本次操作
     */
    @Data
    public class  CallbackSuperResponse{
        /** 操作码 0为允许本次消息发送 1为阻止本次消息发送 */
        int actionCode = 0;
        /** 错误码 0代表APP服务器正常处理响应回调 */
        int errCode = 0 ;
        /** 错误信息 */
        String errMsg;
        /** 本次操作ID */
        String operationID;
    }
    ///////////////////////////////////////////
    ///////               文字过滤
    /////////////////////////////////////////////
    @Data
    public class CallbackWordFilterCommandRequest extends CallbackSuperRequest{
        /** 发送者昵称 */
        String senderNickname;
        /** 发送的消息是单聊还是群聊,单聊为1，群聊为2 */
        Integer sessionType;
        /** 消息类型 ：101表示文本，102表示图片..详细参考消息类型格式描述文档*/
        Integer contentType;
        /** 生成时间戳 */
        Integer createTime;
        /** 消息内容 */
        String content;
        /** 被@的用户列表 */
        String[] atUserList;

        @Override
        public CallbackSuperResponse handler() {
            CallbackSuperResponse handler = super.handler();
            try {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(this.content);
            } catch (SocialBusinessException e) {
                handler.setActionCode(1);
                handler.setErrCode(10); // 文档写的有问题。
                String errorMsg = e.getErrorMsg();
                handler.setErrMsg(errorMsg);
                logger.info("本次发送消息=[{}],有敏感词被过滤=[{}]",this.content,errorMsg);
            }
            return handler;
        }
    }

    /**
     * 响应的content即要替换的全新内容。 <br/>
     * actionCode和errCode都为0以及content不为空才会正常将响应的content替换到消息的content，继续消息的发送。 <br/>
     * 只有actionCode不为0才会停止消息发送，如果actionCode回传不为0切errCode为0， <br/>
     * 则向移动端回复201状态码 errCode会返回透传到客户端，客户端可以对此错误码做特殊处理 <br/>
     */
    @Data
    public class CallbackWordFilterCommandResponse extends CallbackSuperResponse{
        /** 替换后的内容*/
        String content;
    }
    ///////////////////////////////////////////
    ///////               发单聊/群聊消息之前回调
    /////////////////////////////////////////////
    /**
     * 发单聊/群聊消息之前回调
     * 回调指令 发单聊之前回调为 callbackBeforeSendSingleMsgCommand 发群聊之前回调为callbackBeforeSendGroupMsgCommand
     */
    @Data
    public class CallbackBeforeSendSingleMsgCommandRequest extends CallbackWordFilterCommandRequest{
        /** 发送者头像 */
        String senderFaceURL;
        /** 接收者ID */
        String recvID;
    }
    @Data
    public class CallbackMsgModifyCommandRequest extends CallbackSuperRequest{}


    /**
     * 发群聊之前回调为callbackBeforeSendGroupMsgCommand
     */
    public class CallbackBeforeSendGroupMsgCommandRequest extends CallbackBeforeSendSingleMsgCommandRequest{}
    /**
     * actionCode为0 才会正常接受回调响应，继续消息的发送。 只有actionCode不为0才会停止消息发送，如果actionCode回传不为0切errCode为0，则向移动端回复201状态码 errCode会返回透传到客户端，客户端可以对此错误码做特殊处理
     */
    public class CallbackBeforeSendMsgCommandResponse extends CallbackSuperResponse{ }

    ///////////////////////////////////////////
    ///////               发单聊/群聊消息之后回调
    /////////////////////////////////////////////
    /**
     * 发单聊/群聊消息之后回调
     * 回调指令 发单聊之前回调为 callbackBeforeSendSingleMsgCommand 发群聊之前回调为callbackBeforeSendGroupMsgCommand
     */
    public class CallbackAfterSendSingleMsgCommandRequest extends CallbackBeforeSendSingleMsgCommandRequest{}
    /**
     * 发单聊/群聊消息之后回调
     * 发群聊之前回调为callbackBeforeSendGroupMsgCommand
     */
    public class CallbackAfterSendGroupMsgCommandRequest extends CallbackBeforeSendSingleMsgCommandRequest{}
    /**
     * actionCode和errCode不影响本次操作，openIM服务端会忽略actionCode并且在日志中打印errCode
     */
    public class CallbackAfterSendMsgCommandResponse extends CallbackSuperResponse{ }

    ///////////////////////////////////////////
    ///////               用户离线之后回调
    /////////////////////////////////////////////
    @Data
    public class CallbackUserOfflineCommandRequest extends CallbackBaseRequest{
        /** 发送者ID */
        String userID;
        /** 平台ID */
        String PlatformID;
        /** 平台 */
        String Platform;
        /** 序号 服务器毫秒时间戳 */
        Integer seq;
    }

    ///////////////////////////////////////////
    ///////               用户上线之后回调
    /////////////////////////////////////////////
    @Data
    public class CallbackUserOnlineCommandRequest extends CallbackUserOfflineCommandRequest{
        /** 使用token */
        String token;
        /** app是否在后台，true表示在后台，走离线推送 */
        Boolean isAppBackground;
    }

    ///////////////////////////////////////////
    ///////               用户接受离线推送回调
    /////////////////////////////////////////////
    @Data
    public class CallbackOfflinePushCommandRequest extends CallbackUserOfflineCommandRequest{
        /** 发送者ID列表 */
        String[] UserIDList;
        /** 推送标题 */
        String Title;
        /** 推送描述 */
        String desc;
        /** 扩展字段 */
        String ex;
        /** ios推送声音 */
        String iOSPushSound;
        /** 推送消息是否计入桌面图标未读数 */
        String iOSBadgeCount;
        /** 发送id
        String sendID;
        /** 群id */
        String groupID;
        /** 类型 */
        String contentType;
        /** 消息单聊群聊类型 */
        String sessionType;
        /** 内容 */
        String content;
        /** 被@的用户列表 */
        String[] atUserIDList;
    }
    /**
     * actionCode为0 才会正常接受回调响应，继续离线消息的推送。 errCode只在服务端做log打印日志处理。
     *
     * 响应的userIDList为离线推送的用户ID列表
     * actionCode和errCode都为0以及userIDList不为空才会正常离线推送响应的userIDList,
     * 如果userIDList为空且actionCode为0会正常以请求的UserIDList进行离线推送
     */
    @Data
    public class CallbackOfflinePushCommandResponse extends CallbackSuperResponse{
       /** 需要离线推送的用户ID列表 */
        String [] UserIDList;
    }

    ///////////////////////////////////////////
    ///////               用户接受在线推送前回调
    /////////////////////////////////////////////
    public class CallbackOnlinePushCommandRequest extends CallbackUserOfflineCommandRequest{}

    ///////////////////////////////////////////
    ///////               superGroup在线推送前回调请求
    /////////////////////////////////////////////
    public class CallbackSuperGroupOnlinePushCommandRequest extends CallbackUserOfflineCommandRequest{}
    /**
     * actionCode为0 才会正常接受回调响应，继续在线消息的推送。 errCode只在服务端做log打印日志处理。
     *
     * 响应的userIDList推送的用户ID列表 actionCode和errCode都为0以及userIDList不为空才会正常在线推送userIDList, 如果userIDList为空且actionCode为0会正常使用openIM的数据库群成员进行推送
     */
    public class CallbackSuperGroupOnlinePushCommandResponse extends CallbackOfflinePushCommandResponse{}
}
