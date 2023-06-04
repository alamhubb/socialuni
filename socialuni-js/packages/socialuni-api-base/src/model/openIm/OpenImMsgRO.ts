export interface OpenImMsgRO {
    /// 消息id，唯一标识
    clientMsgID: string

/// 服务端生成的id
    serverMsgID: string

/// 创建时间
    createTime: number

/// 发送时间
    sendTime: number

/// 会话类型[ConversationType]
    sessionType: number

/// 发送者id
    sendID: string

/// 接收者id
    recvID: string

/// 来源
    msgFrom: number

/// 消息类型[MessageType]
    contentType: number

/// 平台[Platform]
    platformID: number

/// 发送者昵称
    senderNickname: string

/// 发送者头像
    senderFaceUrl: string

/// 群ID
    groupID: string

/// 消息内容
    content: string

/// 消息的seq
    seq: number

/// 是否已读
    isRead: boolean

/// 已读时间
    hasReadTime: number

/// 消息发送状态[MessageStatus]
    status: number

/// 离线显示内容
    OfflinePushInfo
    offlinePush

/// 附加信息
    attachedInfo: string

/// 扩展信息
    ex: string

/// 自定义扩展信息，目前用于客服端处理消息时间分段
    ext: string

/// 图片
    pictureElem: any

/// 语音
    soundElem: any

/// 视频
    videoElem: any

/// 文件
    fileElem: any

/// @信息
    atElem: any

/// 位置
    locationElem: any

/// 自定义
    customElem: any

/// 引用
    quoteElem: any

/// 合并
    mergeElem: any

/// 通知
    notificationElem: any

/// 自定义表情
    faceElem: any

/// 附加信息
    attachedInfoElem: any
}
