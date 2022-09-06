# api 配置说明

## 前端配置

```shell
# 后端项目的地址
VUE_APP_SOCIALUNI_URL=https://api.socialuni.cn
```

## 后端配置
```yaml
socialuni:
  # 开发者秘钥，接入联盟后，调用联盟接口需要携带此秘钥，从社交联盟开发者管理后台注册申请秘钥  admin.socialuni.cn, 不接入联盟可不填写
  secret-key: "d9a53b2892a540689b4ef608bdca3d2e"
  # 开发者id，uuid生成，全网唯一，联盟用来标识开发者数据唯一性，由联盟提供
  socialuni-id: "d15502ebc20f4f21bf2b375f2b3ac312"
  app:
    # 静态资源的url，拼接图片url使用， 可随意填写
    static-resource-url: "https://devcdxapp-1257733245.file.myqcloud.com"
    # 系统管理员用户id，给用户下发通知时使用， 可随意填写
    system-user-id: 1
  user:
    # 生成用户token时使用的秘钥， 不使用保持默认即可
    token-secret-key: "tokenSecretKey"
    # 使用qq渠道登录时需要输入的内容， 不使用保持默认即可
  provider:
    qq:
      qq-mp-id: ""
      qq-mp-secret: ""
      # 使用qq支持时需要填写的内容， 不使用保持默认即可
      qq-merchant-id: ""
      qq-merchant-key: ""
    # 使用微信渠道登录时需要输入的内容， 不使用保持默认即可
    wx:
      wx-app-id: ""
      wx-mp-id: ""
      # 使用微信支付时需要填写的内容， 不使用保持默认即可
      wx-mp-secret: ""
      wx-merchant-id: ""
      wx-merchant-key: ""
  cloud:
    # 上传图片相关功能，腾讯cos相关配置， 不使用保持默认即可
    tencent:
      cos:
        secret-id: ""
        secret-key: ""
        region: ""
        bucket-name: ""
      # 发送手机验证码相关腾讯云配置， 不使用保持默认即可
      sms:
        app-id: 1
        app-key: ""
        sms-sign: ""
        template-id: 1
  # 腾讯地图定位使用，获取用户地理位置等  ， 不使用保持默认即可
  map:
    qq:
      secret-key: ""
```
