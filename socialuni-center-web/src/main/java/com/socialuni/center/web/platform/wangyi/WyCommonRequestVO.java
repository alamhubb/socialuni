package com.socialuni.center.web.platform.wangyi;

public class WyCommonRequestVO {
    //产品秘钥 id ，由易盾内容安全服务分配，产品标识
    private String secretId;
    //业务id ，由易盾内容安全服务分配，业务标识
    private String businessId;
    //请求当前 UNIX 时间戳，请注意服务器时间是否同步
    private String timestamp;
    //随机整数，与 timestamp 联合起来，用于防止重放攻击
    private String nonce;
    //指定签名算法，国密：SM3,如果使用md5默认签名算法，可以不传
    private String signatureMethod;
    //请求签名，用来验证此次请求的合法性，具体算法见 接口鉴权
    private String signature;
}
