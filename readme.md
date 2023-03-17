## 产品体验，已上线两款产品，清池和小星星

### 开发者十分钟就可以基于此项目开发一款与展示项目相同的小程序
* 有自己项目，或者想自己做个项目的朋友欢迎入群交流，大家一起讨论变现，接广告，如何做好产品相关问题

<table>
  <thead>
  <tr>
    <th>我的微信，进社交软件app交流群可以加我，互相学习，讨论问题</th>
    <th>清池</th>
    <th>小星星</th>
  </tr>
  </thead>
  <tbody>
  <tr>
      <td align="center" valign="middle">
        <img width="222px" src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/wxcode.png">
      </td>
      <td align="center" valign="middle">
        <img width="222px" src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/qingchiwxcode.jpg!thumbnail">
      </td>
      <td align="center" valign="middle">
         <img width="222px" src="https://cdxapp-1257733245.file.myqcloud.com/socialuni/ministar/ministarwxmpcode.jpg!thumbnail">
      </td>
    </tr>
  <tr></tr>
  </tbody>
</table>

### 文档相关网站地址
#### 社交联盟官网 [https://socialuni.cn](https://socialuni.cn)
#### 演示demo系统 [https://socialuni.cn/demo](https://socialuni.cn/demo)
#### 开放接口api文档 [开放接口api文档](https://console-docs.apipost.cn/preview/940ead4467df9d6d/0e3f3c24b231f818?target_id=b78d2016-0442-4a2f-9588-953a364bd21c)
#### 极速体验-点击url获取JSON格式用户动态数据列表 -> [https://api.socialuni.cn/socialuni/talk/queryTalks](https://api.socialuni.cn/socialuni/talk/queryTalks)
#### 后台管理系统地址 [https://admin.socialuni.cn](https://admin.socialuni.cn)
* 完整的接入社交联盟，从社交联盟获取数据需要在后台管理系统中注册成为开发者，获取开发者秘钥，携带秘钥访问社交联盟API获取数据
#### 项目gitee地址 [https://gitee.com/socialuni/socialuni](https://gitee.com/socialuni/socialuni)
#### 项目github地址 [https://github.com/social-uni/socialuni](https://github.com/social-uni/socialuni)

## 客户端功能点

| 功能点              | 是否支持 | 配置类属性 |
|:------------------|:------|:-------|
| 社区功能，帖子，评论，点赞    | √    | 无     |
| 帖子筛选，年龄，性别，城市，定位 | √    | 无     |
| 圈子               | √    | 无     |
| 话题               | √    | 无     |
| 校园               | √    | 无     |
| 首页tabs可配置        | √    | SocialuniAppConfig.appConfig.tabNames     |
| 禁止发表未成年人内容       | √    | SocialuniAppConfig.appConfig.disableUnderageContent     |
| 禁止发表包含联系方式的内容    | √    | SocialuniAppConfig.appConfig.disableContentHasContactInfo     |
| 禁止发表包含二维码的内容     | √    | SocialuniAppConfig.appConfig.disableContentHasQrCode     |
| 必须设置校园才能发帖       | √    | SocialuniAppConfig.appConfig.mustSetSchoolCanPost     |
| 违禁词管理            | √    | 无     |
| 敏感词管理            | √    | 无     |
| 必须实名认证才能发帖       | √    | SocialuniAppConfig.appConfig.mustIdentityAuthCanPost     |
| 仅(男生，女生)性别社区     | ×    | SocialuniAppConfig.appConfig.appGender     |
| 付费获取联系方式    | ×    |      |

## 后台管理功能点
| 功能点           | 是否支持 | 配置类属性 |
|---------------|------|-------|
| 支持内容审核        | √    | 无     |
| 支持添加敏感词       | √    | 无     |
| 支持实名认证审核      | √    | 无     |

## 入门

### 社交联盟是什么？

社交联盟是开发社交软件的一套集成解决方案，使您可以快速搭建一款满足运营要求的社交软件，社交联盟提供开源的社交软件代码模板和开放非隐私的用户社交数据供您使用

### 代码架构
* 社交联盟基于 uniapp 和 java开发，支持快速发布小程序、app、web各平台
* 前端 uniapp + vue2 +ts
* 后端 java + springboot + mysql + redis

### 快速开始
社交联盟代码模板分为前后端两个模块，本示例仅展示独立项目使用方式，如需要嵌入已有系统，请参考[更多用法](#更多用法)


#### 前端模块

1. 打开socialuni目录中的socialuni-js项目

2. 在socialuni-js项目的根目录执行
```
npm install
```
3. install成功后执行，控制台出现下图内容则代表启动成功
 ```
npm run serve
```
![img_3.png](https://cdxapp-1257733245.file.myqcloud.com/socialuni/doc/readme/img_3.png)
4. 使用浏览器打开 [http://localhost:8024/](http://localhost:8024/)，出现此图，则代表项目启动成功

![img_4.png](https://cdxapp-1257733245.file.myqcloud.com/socialuni/doc/readme/img_4.png)

#### 后端模块

1. 使用git clone https://gitee.com/socialuni/socialuni.git
2. 打开socialuni项目，使用maven，install 安装socialuni-parent目录，安装后出现下图中内容则代表安装成功

![img_2.png](https://cdxapp-1257733245.file.myqcloud.com/socialuni/doc/readme/img_2.png)

3. 安装成功后，启动socialuni-web项目，控制台出现以下内容则项目启动成功

![img.png](https://cdxapp-1257733245.file.myqcloud.com/socialuni/doc/readme/img.png)

4. 在浏览器中输入 [https://localpc.socialuni.cn:8091/socialuni/talk/queryTalks](https://localpc.socialuni.cn:8091/socialuni/talk/queryTalks)

![img_1.png](https://cdxapp-1257733245.file.myqcloud.com/socialuni/doc/readme/img_1.png)

看到以下内容，则代表成功从中心获取到了动态数据

5. 然后将前端项目配置的后端地址由 https:api.socialuni.cn 改为本地的 https:localpc.socialuni.cn:8091 即可访问本地的后端项目

#### 更多用法

社交联盟代码模板分为前后端两个模块，本示例仅展示独立项目使用方式，如需要嵌入已有系统，请参考演示系统
* [演示系统地址](https://socialuni.cn/demo)
* [演示系统后端代码](https://gitee.com/socialuni/socialuni/tree/master/socialuni-official-web)
* [演示系统前端代码](https://gitee.com/socialuni/socialuni/blob/master/socialuni-docs/src/views/demo/DemoView.vue)

## 更新记录

**当前版本V0.31-2023.3.15**

###### **V0.31发布-2023.3.15**
1. 支持openIm聊天功能
2. 支持商业化充值功能
3. 支持粉丝和关注列表
4. 支持扩列功能界面

###### **V0.3发布-2022.11.3**
1. 项目结构调整，升级vue3版本

###### **V0.25发布-2022.10.16**
1. 支持在后台管理配置各类配置项

###### **V0.24发布-2022.4.1**
1. 动态支持配置项
2. 是否禁止未成年人内容
3. 包含人图像是否需要认证

###### **V0.23发布-2022.3.15**
1. 开发圈子功能

###### **V0.22发布-2021.10.18**
1. 上线小星星，支持主题配置
2. 开放社交联盟数据接口

###### **V0.21发布-2021.7.20**
1. 上线社交联盟官网

###### **V0.2发布-2021.5.20**
1. 多租户功能开发

###### **V0.19发布-2021.4.13**
1. 支持单一性别类型，女生社区，男生社区

###### **V0.18发布-20     21.1.11**
1. 整理违规关键词，提升自动审核违规率，提高内容质量，过滤垃圾信息

###### **V0.17发布-2020.11.22**
1. 上线内容安全功能，发布内容违禁词拦截，敏感词预审核

###### **V0.16发布-2020.7.4**
1. 首页动态支持，年龄，城市，性别筛选

###### **V0.15发布-2020.6.2**
1. 修复性能问题，支持缓存

###### **V0.14发布-2020.4.13**
1. 支持系统日志功能 上线ip限制功能

###### **V0.13发布-2020.3.3**
1. 支持举报功能
2. 支持关注功能
3. 支持流量主广告功能

###### **V0.12发布-2019.12.23**
1. 支持通知功能

###### **V0.11发布-2019.11.17**
1. 支持展示附近功能，展示附近的帖子
2. 支持话题功能，发帖时可选择话题

###### **V0.1发布-2019.10.16**
1. 支持用户注册编辑、社区模块发帖评论点赞功能


## 前排寻求对社交和im感兴趣的小伙伴一同合作开发

## 接基于本项目的社交软件部署搭建和二次开发

## 愿景，让社交软件开发更容易，提供用户模块、社区模块，im模块三部分独立的sdk，前端组件，提供社交软件需要的用户数据、运营、商业化变现支持

## 社交联盟项目，提供中心化的用户动态数据，提供社交项目前后端模板，帮助开发者快速开发一个满足运营需求，有真实用户和流量的社交软件
