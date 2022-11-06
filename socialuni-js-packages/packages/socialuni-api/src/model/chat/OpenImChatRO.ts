export interface OpenImChatRO {
    /// 会话ID
    conversationID: string

/// 会话类型[ConversationType]
    conversationType: number

/// 参与会话的userID
    userID: string

/// 参与会话的groupID
    groupID: string

/// 昵称
    showName: string

/// 头像
    faceURL: string

/// 免打扰 0：正常；1：不接受消息；2：接受在线消息不接受离线消息；
    recvMsgOpt: number

/// 未读消息数
    unreadCount: number

/// 强制提示，[GroupAtType]包含@所有人，@个人以及公告提示
    groupAtType: number

/// 会话最新消息内容
    Message?
    latestMsg

/// 最新消息发送时间
    latestMsgSendTime: number

/// 草稿
    draftText: string

/// 草稿生成时间
    draftTextTime: boolean

/// 是否置顶
    isPinned: boolean

/// 是否开启了私聊（阅后即焚）
    isPrivateChat: boolean

/// 附加内容
    ext: string

/// 是否还在组内，如果退群返回true
    isNotInGroup: boolean
}
