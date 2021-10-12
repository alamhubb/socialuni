程序运行层级
controller -> com.socialuni.web.service -> domain -> domain -> manager -> entity -> store -> repository

controller 无业务，接收入参，转发service

//多个domain
com.socialuni.web.service 调度，

//多个entity
domain 多实体业务

//一个entity的操作层
manager 本实体业务

//构建dao
entity 轻业务

//直接处理dao
store  缓存，sql处理，调度存储

//存储层
repository