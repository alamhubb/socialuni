# 配置腾讯cos
## 感谢灵辉编写详细的配置（踩坑）手册

## 开通腾讯cos

- 公有读私有写
- 其他都是默认的

![image-20210114210233079](./assets/image-20210114210233079.png)

![image-20210114224321428](./assets/image-20210114224321428.png)

![image-20210115135611628](./assets/image-20210115135611628.png)

![image-20210115113130971](./assets/image-20210115113130971.png)

```txt

avatar imageMogr2/thumbnail/100x/interlace/0|imageMogr2/gravity/center/crop/100x100 
normal imageMogr2/thumbnail/800x/interlace/1 
thumbnail imageMogr2/thumbnail/300x/interlace/0 

```





## 前端配置

![image-20210114210705871](./assets/image-20210114210705871.png)

## 后端配置

![image-20210114213235383](./assets/image-20210114213235383.png)

>  注意imgUrl地址为: region  加一个/



# 腾讯短信

## 开通短信

![image-20210115114929723](./assets/image-20210115114929723.png)

![image-20210115114829843](./assets/image-20210115114829843.png)

![image-20210115114734520](./assets/image-20210115114734520.png)

## 后端配置

- https://cloud.tencent.com/document/product/382/13613

![image-20210115114624531](./assets/image-20210115114624531.png)



# 上线打包说明

## 前端配置

![image-20210116135846705](./assets/image-20210116135846705.png)

> 把配置项到生产配置一遍，打包正常云打包就行了。
