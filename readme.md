## 社交联盟项目模板(包含前后端代码)

项目地址 https://gitee.com/socialuni/socialuni

前端代码使用 socialuni-js项目 https://gitee.com/socialuni/socialuni/tree/master/socialuni-js
后端代码使用 socialuni-demo项目 https://gitee.com/socialuni/socialuni/tree/master/socialuni-demo

##### 目前仅支持接入社交联盟的使用方式

数据存两份，开发者数据库一份，联盟一份，未来会拆开这个逻辑，支持不接入联盟的方式

### 使用方式:

1. 首先前往社交联盟注册成为开发者，https://admin.qingchiapp.com/
2. 修改前端项目内容，修改socialuni-js项目src下的manifest.json中的mp-weixin，app-id改为自己的小程序appId
后端修改内容
3. 修改后端项目内容，修改socialuni-demo项目src下的application-demo中的
socialuni:
    secret-key: ""，设置为自己的社交联盟秘钥
   
然后分别启动后端和前端你项目，即可体验