# 以下属于对该项目改造的日记
# 时间点从下往上看。



# 领域事件问题.
## 背景
需要openIm（也有用户模块）整合。 在当前项目的用户注册/登录时需要调用openIm同步过去。
[APP与OpenIM的关系](https://doc.rentsoft.cn/#/framework/relationship?id=open-im用户登录)
## 领域事件的引入.
- 为了扩展和异步性。也应该引入事件。


# API和 controller 层的界线和使用 
## 背景
老哥强迫症，把一些多余的服务也放到了API层。 进而引用了对应的DO和jpa又依赖啦。
## 部分功能不同领域不同表现
例如: 获得租户领域的配置 {@code ConfigAPI}
基于数据库实现，可以有增删改。但是基于配置文件的是没有的。  这时的增删改不能抽象到API层。 只能再uni模块的 controller 中实现。和对应的前端进行联调。

## 规范
不能抽象到API层的放到自己实现层中的controller做特殊功能处理。


# 以数据库表为领域,最小化拆分。
## 背景
- 个人认为优秀的重构，不是从头再来。而是一点点模块的改进。同时业务应该继续往前发展。
- 该项目一般都是以业务来做的表结构设计。 所以优先改的内容应该是dao和entity的发展。
- 本人半路加进来，之前的设计完全不懂，重头梳理代码和业务没有必要。 一点点改进一点点熟悉项目。

## 架构定稿
- 还是采用ddd的设计思想去做的。主要是为了解耦。重点和痛点就是解耦的问题。
- 数据采用双写的机制往开发者数据库里面去写数据。
- 先把表结构认为是业务模型，对之前的jpa做拆分。（同时也是一个熟悉项目的过程。） 这里属于最小版的改造。
- 应该做成和jdbc一样,接口标准化允许多实现。 
### 老哥喜欢用静态类,不用DI
谈论了一下其实就是一个门面的思想（其实更好叫委托）。 
简化编码和统一入口代码。 

### 大致的实现包名
sdk 模块就2包: api、model ； 原则不允许依赖任何包，只能有sdk的包。如spring、db等等。
dev 实现模块也就2包:  repository(仓库,继承api和jpa) 、 entity(实体继承model) ；  

- 泛型的坑 ： 因为对外都是用的api和model 。 和jpa方法相同时编译过不去，所以api用  List< ?  extends  xxxModel>
- 这老哥还是直接用的repository里面的类。  所以引入包具体实现 maven加入 <scope>runtime</scope> ，编译是不存在，只在运行时用。

###  表转领域实践的心得
- 新建sdk和dev模块,再建立对应的包名, 引入sdk和dev依赖。
- sdk模块先引入common（先把公共的东西暂时放到这个模块中）的模块，避免idea报错。
1. 移动包DO。  如果有枚举就先移动枚举值。
2. 移动包Repository。 

- 新建dev模块，引入common模块。先不要引入sdk，防止idea。
3. copyDO
4. copyRepository
- 引入sdk的包，先把报错解决掉。

5. 修改sdk的文件名和把里面依赖spring等的内容去掉和排除依赖。
6. 修改dev实现包中的内容。改成继承形式。
- 先改entity。
- 再改repository。 注意里面引入的Do内容是不是被改变啦。

- 遇到保存save的方法需要做方法重名。加默认实现。 否则方法重载生成代理类会报Model实体jpa找不到错。
```
    default TagDO savePut(TagDO TagDO){
        return this.save(BeanUtil.toBean(TagDO,TagDO.class));
    }
```

