

## 修改logo信息







## 打包

- 运行命令行

`npm run build:mp-weixin`

- 报错可以忽略

![image-20211029092651925](social-js小程序上线.assets/image-20211029092651925.png)

![image-20211029092713700](social-js小程序上线.assets/image-20211029092713700.png)

### 小程序设置

- 开发管理 -》 开发设置 -》 服务器域名

![image-20211029095518540](social-js小程序上线.assets/image-20211029095518540.png)

```
request合法域名: 
https://api.map.baidu.com;https://api.qingchi1.com;https://api.socialuni.cn;https://apis.map.qq.com;https://cdn.bootcss.com;https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://devcdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://h5.shangjb.com;https://localpc.socialuni.cn;https://ministar.socialuni.cn;https://mp.socialuni.cn;https://qkypc.qingchi1.com;https://qkypc.qingchi1.com:8091;https://qkypc.socialuni.cn;https://qkypc.socialuni.cn:8091;https://qkypc.socialuni.cn:8092;https://restapi.amap.com;https://social.socialuni.cn;https://tongji.dcloud.io;https://www.mxnzp.com;https://www.qingchi1.com;https://www.qingchi1.com:8091;https://www.socialuni.cn;

socket合法域名:
wss://api.qingchi1.com;wss://api.socialuni.cn;wss://h5.shangjb.com;wss://localpc.socialuni.cn;wss://ministar.socialuni.cn;wss://mp.socialuni.cn;wss://qkypc.qingchi1.com;wss://qkypc.qingchi1.com:8091;wss://qkypc.socialuni.cn;wss://qkypc.socialuni.cn:8091;wss://qkypc.socialuni.cn:8092;wss://social.socialuni.cn;wss://www.qingchi1.com;wss://www.qingchi1.com:8091;wss://www.socialuni.cn;

uploadFile合法域名:
https://api.qingchi1.com;https://api.socialuni.cn;https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://devcdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://h5.shangjb.com;https://localpc.socialuni.cn;https://ministar.socialuni.cn;https://mp.socialuni.cn;https://qkypc.qingchi1.com;https://qkypc.qingchi1.com:8091;https://qkypc.socialuni.cn;https://qkypc.socialuni.cn:8091;https://qkypc.socialuni.cn:8092;https://social.socialuni.cn;https://www.qingchi1.com;https://www.qingchi1.com:8091;https://www.socialuni.cn;

downloadFile合法域名:
https://api.qingchi1.com;https://api.socialuni.cn;https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://devcdxapp-1257733245.cos.ap-beijing.myqcloud.com;https://localpc.socialuni.cn;https://ministar.socialuni.cn;https://mp.socialuni.cn;https://qkypc.qingchi1.com;https://qkypc.qingchi1.com:8091;https://qkypc.socialuni.cn;https://qkypc.socialuni.cn:8091;https://qkypc.socialuni.cn:8092;https://social.socialuni.cn;https://www.qingchi1.com;https://www.qingchi1.com:8091;https://www.socialuni.cn;


```



- 微信登录，秘钥生成。。
  ![image-20211029171456415](social-js小程序上线.assets/image-20211029171456415.png)

![image-20211029171408441](social-js小程序上线.assets/image-20211029171408441.png)

- UGC申明: 

![image-20211029095146487](social-js小程序上线.assets/image-20211029095146487.png)

> 审核机制说明， 使用腾讯云的内容安全产品，有官方审查人员对违规内容进行审查。
> 提供用户举报功能，用户可以对于违规内容进行举报，举报后有运营人员进行审核，对违规内容处理



- 导入项目

![image-20211029092810450](social-js小程序上线.assets/image-20211029092810450.png)

![image-20211029095941136](social-js小程序上线.assets/image-20211029095941136.png)

