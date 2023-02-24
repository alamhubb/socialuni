import {RequestFunc} from "../constants";

export enum MessageStatus {
    Sending = 1,
    Succeed = 2,
    Failed = 3,
}

export enum Platform {
    iOS = 1,
    Android = 2,
    Windows = 3,
    MacOSX = 4,
    Web = 5,
    Linux = 7,
    Admin = 8,
}

export enum MessageType {
    TEXTMESSAGE = 101,
    PICTUREMESSAGE = 102,
    VOICEMESSAGE = 103,
    VIDEOMESSAGE = 104,
    FILEMESSAGE = 105,
    ATTEXTMESSAGE = 106,
    MERGERMESSAGE = 107,
    CARDMESSAGE = 108,
    LOCATIONMESSAGE = 109,
    CUSTOMMESSAGE = 110,
    REVOKEMESSAGE = 111,
    HASREADRECEIPTMESSAGE = 112,
    TYPINGMESSAGE = 113,
    QUOTEMESSAGE = 114,
    FACEMESSAGE = 115,
    ADVANCETEXTMESSAGE = 117,
    ADVANCEREVOKEMESSAGE = 118,
    CUSTOMMSGNOTTRIGGERCONVERSATION = 119,
    CUSTOMMSGONLINEONLY = 120,
    FRIENDAPPLICATIONAPPROVED = 1201,
    FRIENDAPPLICATIONREJECTED = 1202,
    FRIENDAPPLICATIONADDED = 1203,
    FRIENDADDED = 1204,
    FRIENDDELETED = 1205,
    FRIENDREMARKSET = 1206,
    BLACKADDED = 1207,
    BLACKDELETED = 1208,
    SELFINFOUPDATED = 1303,
    NOTIFICATION = 1400,
    GROUPCREATED = 1501,
    GROUPINFOUPDATED = 1502,
    JOINGROUPAPPLICATIONADDED = 1503,
    MEMBERQUIT = 1504,
    GROUPAPPLICATIONACCEPTED = 1505,
    GROUPAPPLICATIONREJECTED = 1506,
    GROUPOWNERTRANSFERRED = 1507,
    MEMBERKICKED = 1508,
    MEMBERINVITED = 1509,
    MEMBERENTER = 1510,
    GROUPDISMISSED = 1511,
    GROUPMEMBERMUTED = 1512,
    GROUPMEMBERCANCELMUTED = 1513,
    GROUPMUTED = 1514,
    GROUPCANCELMUTED = 1515,
    GROUPMEMBERINFOUPDATED = 1516,
    BURNMESSAGECHANGE = 1701,
}

export enum SessionType {
    Single = 1,
    Group = 2,
    SuperGroup = 3,
    Notification = 4,
}

export enum GroupJoinSource {
    Invitation = 2,
    Search = 3,
    QrCode = 4,
}


export interface InitConfig {
    userID: string;
    token: string;
    url: string;
    platformID: number;
    isBatch?: boolean;
    operationID?: string;
};

export interface WsParams {
    reqFuncName: RequestFunc;
    operationID: string;
    userID: string | undefined;
    data: any;
};

export interface WsResponse {
    event: RequestFunc;
    errCode: number;
    errMsg: string;
    data: any;
    operationID: string;
};

export interface LoginParams {
    userID: string;
    token: string;
};

export interface AdvancedMsgParams {
    text: string;
    messageEntityList?: MessageEntity[];
};

export interface MessageEntity {
    type: string;
    offset: number;
    length: number;
    url?: string;
    info?: string;
};

export interface AtMsgParams {
    text: string;
    atUserIDList: string[];
    atUsersInfo?: AtUsersInfoItem[];
    message?: string;
};

export interface AtUsersInfoItem {
    atUserID: string;
    groupNickname: string;
};

export interface ImageMsgParams {
    sourcePicture: PicBaseInfo;
    bigPicture: PicBaseInfo;
    snapshotPicture: PicBaseInfo;
};

export interface PicBaseInfo {
    uuid: string;
    type: string;
    size: number;
    width: number;
    height: number;
    url: string;
};

export interface SoundMsgParams {
    uuid: string;
    soundPath: string;
    sourceUrl: string;
    dataSize: number;
    duration: number;
};

export interface VideoMsgParams {
    videoPath: string;
    duration: number;
    videoType: string;
    snapshotPath: string;
    videoUUID: string;
    videoUrl: string;
    videoSize: number;
    snapshotUUID: string;
    snapshotSize: number;
    snapshotUrl: string;
    snapshotWidth: number;
    snapshotHeight: number;
};

export interface FileMsgParams {
    filePath: string;
    fileName: string;
    uuid: string;
    sourceUrl: string;
    fileSize: number;
};

export interface FileMsgFullParams {
    fileFullPath: string;
    fileName: string;
};

export interface VideoMsgFullParams {
    videoFullPath: string;
    videoType: string;
    duration: number;
    snapshotFullPath: string;
};

export interface SouondMsgFullParams {
    soundPath: string;
    duration: number;
};

export interface MergerMsgParams {
    messageList: MessageItem[];
    title: string;
    summaryList: string[];
};

export interface FaceMessageParams {
    index: number;
    data: string;
};

export interface LocationMsgParams {
    description: string;
    longitude: number;
    latitude: number;
};

export interface CustomMsgParams {
    data: string;
    extension: string;
    description: string;
};

export interface QuoteMsgParams {
    text: string;
    message: string;
};

export interface AdvancedQuoteMsgParams {
    text: string;
    message: string;
    messageEntityList: MessageEntity[]
};

export interface SendMsgParams {
    recvID: string;
    groupID: string;
    offlinePushInfo?: OfflinePush;
    message: string;
};

export interface GetHistoryMsgParams {
    userID: string;
    groupID: string;
    count: number;
    startClientMsgID: string;
    conversationID?: string;
};

export interface GetAdvancedHistoryMsgParams {
    userID: string;
    groupID: string;
    count: number;
    startClientMsgID: string;
    conversationID?: string;
    lastMinSeq: number;
};

export interface setPrvParams {
    conversationID: string;
    isPrivate: boolean;
};

export interface InsertSingleMsgParams {
    message: string;
    recvID: string;
    sendID: string;
};

export interface InsertGroupMsgParams {
    message: string;
    groupID: string;
    sendID: string;
};

export interface TypingUpdateParams {
    recvID: string;
    msgTip: string;
};

export interface MarkC2CParams {
    userID: string;
    msgIDList: string[];
};

export interface MarkNotiParams {
    conversationID: string;
    msgIDList: string[];
};

export interface SplitParams {
    offset: number;
    count: number;
};

export interface GetOneCveParams {
    sourceID: string;
    sessionType: number;
};

export interface SetDraftParams {
    conversationID: string;
    draftText: string;
};

export interface PinCveParams {
    conversationID: string;
    isPinned: boolean;
};

export interface isRecvParams {
    conversationIDList: string[];
    opt: any;
};

export enum Optinterface {
    Nomal = 0,
    Mute = 1,
    WithoutNotify = 2,
}

export interface SearchLocalParams {
    conversationID: string;
    keywordList: string[];
    keywordListMatchType?: number;
    senderUserIDList?: string[];
    messageTypeList?: MessageType[];
    searchTimePosition?: number;
    searchTimePeriod?: number;
    pageIndex?: number;
    count?: number;
};

export interface AddFriendParams {
    toUserID: string;
    reqMsg: string;
};

export interface SearchFriendParams {
    keywordList: string[];
    isSearchUserID: boolean;
    isSearchNickname: boolean;
    isSearchRemark: boolean;
};

export interface AccessFriendParams {
    toUserID: string;
    handleMsg: string;
};

export interface RemarkFriendParams {
    toUserID: string;
    remark: string;
};

export interface InviteGroupParams {
    groupID: string;
    reason: string;
    userIDList: string[];
};

export interface GroupMsgReadParams {
    groupID: string;
    msgIDList: string[];
};

export interface GetGroupMemberParams {
    groupID: string;
    filter: number;
    offset: number;
    count: number;
};

export interface GetGroupMemberByTimeParams {
    groupID: string;
    filterUserIDList: string[];
    offset: number;
    count: number;
    joinTimeBegin: number;
    joinTimeEnd: number;
};

export interface SearchGroupMemberParams {
    groupID: string;
    keywordList: string[];
    isSearchUserID: boolean;
    isSearchMemberNickname: boolean;
    offset: number;
    count: number;
};

export interface SetMemberAuthParams {
    rule: any;
    groupID: string;
}

export interface CreateGroupParams {
    groupBaseInfo: GroupInitInfo;
    memberList: Member[];
};

export interface GroupInitInfo {
    groupType: any;
    groupName: string;
    introduction?: string;
    notification?: string;
    faceURL?: string;
    ex?: string;
};

export interface Member {
    userID: string;
    roleLevel: number;
};

export interface GroupInfoParams {
    groupID: string;
    groupInfo: GroupBaseInfo;
};

export interface MemberNameParams {
    groupID: string;
    userID: string;
    GroupMemberNickname: string;
};

export interface GroupBaseInfo extends Partial<Omit<GroupInitInfo, "groupType">> {

}

export interface JoinGroupParams {
    groupID: string;
    reqMsg: string;
    joinSource: GroupJoinSource;
};

export interface SearchGroupParams {
    keywordList: string[];
    isSearchGroupID: boolean;
    isSearchGroupName: boolean;
};

export interface ChangeGroupMuteParams {
    groupID: string;
    isMute: boolean;
};

export interface ChangeGroupMemberMuteParams {
    groupID: string;
    userID: string;
    mutedSeconds: number;
};

export interface TransferGroupParams {
    groupID: string;
    newOwnerUserID: string;
};

export interface AccessGroupParams {
    groupID: string;
    fromUserID: string;
    handleMsg: string;
};

export interface SetGroupRoleParams {
    groupID: string;
    userID: string;
    roleLevel: GroupRole;
};

export interface SetGroupVerificationParams {
    verification: GroupVerificationType;
    groupID: string;
};

export interface RtcInvite {
    inviterUserID: string;
    inviteeUserIDList: string[];
    groupID: string;
    roomID: string;
    timeout: number;
    mediaType: string;
    sessionType: number;
    platformID: number;
};

export interface RtcActionParams {
    opUserID: string;
    invitation: RtcInvite;
};

export interface Ws2Promise {
    oid: string;
    mname: string;
    mrsve: (value: WsResponse | PromiseLike<WsResponse>) => void;
    mrjet: (reason?: any) => void;
    flag: boolean;
};

export interface GroupApplicationItem {
    createTime: number;
    creatorUserID: string;
    ex: string;
    gender: number;
    groupFaceURL: string;
    groupID: string;
    groupName: string;
    groupType: number;
    handleResult: number;
    handleUserID: string;
    handledMsg: string;
    handledTime: number;
    introduction: string;
    memberCount: number;
    nickname: string;
    notification: string;
    ownerUserID: string;
    reqMsg: string;
    reqTime: number;
    status: number;
    userFaceURL: string;
    userID: string;
};

export interface FriendApplicationItem {
    createTime: number;
    ex: string;
    fromFaceURL: string;
    fromGender: number;
    fromNickname: string;
    fromUserID: string;
    handleMsg: string;
    handleResult: number;
    handleTime: number;
    handlerUserID: string;
    reqMsg: string;
    toFaceURL: string;
    toGender: number;
    toNickname: string;
    toUserID: string;
};

export interface TotalUserStruct {
    blackInfo: BlackItem | null;
    friendInfo: FriendItem | null;
    publicInfo: PublicUserItem | null;
};

export interface PublicUserItem {
    gender: number;
    nickname: string;
    userID: string;
    faceURL: string;
    ex: string;
};

export interface FullUserItem {
    birth: number;
    createTime: number;
    email: string;
    ex: string;
    faceURL: string;
    gender: number;
    nickname: string;
    phoneNumber: string;
    userID: string;
};

export interface PartialUserItem extends Partial<Omit<FullUserItem, "userID"> & { userID: string }> {

}

export interface FriendItem {
    addSource: number;
    birth: number;
    createTime: number;
    email: string;
    ex: string;
    faceURL: string;
    userID: string;
    gender: number;
    nickname: string;
    operatorUserID: string;
    ownerUserID: string;
    phoneNumber: string;
    remark: string;
};

export interface FriendRelationItem {
    result: number;
    userID: string;
};

export interface BlackItem {
    addSource: number;
    userID: string;
    createTime: number;
    ex: string;
    faceURL: string;
    gender: number;
    nickname: string;
    operatorUserID: string;
    ownerUserID: string;
};

export interface GroupItem {
    groupID: string;
    groupName: string;
    notification: string;
    notificationUserID: string;
    notificationUpdateTime: number;
    introduction: string;
    faceURL: string;
    ownerUserID: string;
    createTime: number;
    memberCount: number;
    status: GroupStatus;
    creatorUserID: string;
    groupType: number;
    needVerification: any;
    ex: string;
    applyMemberFriend: any;
    lookMemberInfo: any;
};

export enum Allowinterface {
    Allowed,
    NotAllowed,
}

export enum Groupinterface {
    NomalGroup,
    SuperGroup,
    WorkingGroup,
}

export enum GroupVerificationinterface {
    ApplyNeedInviteNot,
    AllNeed,
    AllNot,
}

export enum GroupStatus {
    Nomal = 0,
    Baned = 1,
    Dismissed = 2,
    Muted = 3,
}

export interface GroupMemberItem {
    groupID: string;
    userID: string;
    nickname: string;
    faceURL: string;
    roleLevel: GroupRole;
    muteEndTime: number;
    joinTime: number;
    joinSource: GroupJoinSource;
    inviterUserID: string;
    operatorUserID: string;
    ex: string;
};

export enum GroupJoinSource {
    Invitation = 2,
    Search = 3,
    QrCode = 4,
}

export enum GroupRole {
    Nomal = 1,
    Owner = 2,
    Admin = 3,
}

export interface ConversationItem {
    conversationID: string;
    conversationType: SessionType;
    userID: string;
    groupID: string;
    showName: string;
    faceURL: string;
    recvMsgOpt: any;
    unreadCount: number;
    groupAtType: any;
    latestMsg: string;
    latestMsgSendTime: number;
    draftText: string;
    draftTextTime: number;
    isPinned: boolean;
    isNotInGroup: boolean;
    isPrivateChat: boolean;
    attachedInfo: string;
    ex: string;
};

export enum GroupAtinterface {
    AtNormal = 0,
    AtMe = 1,
    AtAll = 2,
    AtAllAtMe = 3,
    AtGroupNotice = 4,
}

export interface MessageItem {
    clientMsgID: string;
    serverMsgID: string;
    createTime: number;
    sendTime: number;
    sessionType: SessionType;
    sendID: string;
    recvID: string;
    msgFrom: number;
    contentType: MessageType;
    platformID: Platform;
    senderNickname: string;
    senderFaceUrl: string;
    groupID: string;
    content: string;
    seq: number;
    isRead: boolean;
    status: MessageStatus;
    offlinePush: OfflinePush;
    attachedInfo: string;
    attachedInfoElem: AttachedInfoElem;
    ex: string;
    pictureElem: PictureElem;
    soundElem: SoundElem;
    videoElem: VideoElem;
    fileElem: FileElem;
    faceElem: FaceElem;
    mergeElem: MergeElem;
    atElem: AtElem;
    locationElem: LocationElem;
    customElem: CustomElem;
    quoteElem: QuoteElem;
    notificationElem: NotificationElem;
    progress?: number;
    downloadProgress?: number;
    downloaded?: boolean;
};

export enum MessageStatus {
    Sending = 1,
    Succeed = 2,
    Failed = 3,
}

export enum Platform {
    iOS = 1,
    Android = 2,
    Windows = 3,
    MacOSX = 4,
    Web = 5,
    Linux = 7,
    Admin = 8,
}

export enum Messageinterface {
    TEXTMESSAGE = 101,
    PICTUREMESSAGE = 102,
    VOICEMESSAGE = 103,
    VIDEOMESSAGE = 104,
    FILEMESSAGE = 105,
    ATTEXTMESSAGE = 106,
    MERGERMESSAGE = 107,
    CARDMESSAGE = 108,
    LOCATIONMESSAGE = 109,
    CUSTOMMESSAGE = 110,
    REVOKEMESSAGE = 111,
    HASREADRECEIPTMESSAGE = 112,
    TYPINGMESSAGE = 113,
    QUOTEMESSAGE = 114,
    FACEMESSAGE = 115,
    ADVANCETEXTMESSAGE = 117,
    ADVANCEREVOKEMESSAGE = 118,
    CUSTOMMSGNOTTRIGGERCONVERSATION = 119,
    CUSTOMMSGONLINEONLY = 120,
    FRIENDAPPLICATIONAPPROVED = 1201,
    FRIENDAPPLICATIONREJECTED = 1202,
    FRIENDAPPLICATIONADDED = 1203,
    FRIENDADDED = 1204,
    FRIENDDELETED = 1205,
    FRIENDREMARKSET = 1206,
    BLACKADDED = 1207,
    BLACKDELETED = 1208,
    SELFINFOUPDATED = 1303,
    NOTIFICATION = 1400,
    GROUPCREATED = 1501,
    GROUPINFOUPDATED = 1502,
    JOINGROUPAPPLICATIONADDED = 1503,
    MEMBERQUIT = 1504,
    GROUPAPPLICATIONACCEPTED = 1505,
    GROUPAPPLICATIONREJECTED = 1506,
    GROUPOWNERTRANSFERRED = 1507,
    MEMBERKICKED = 1508,
    MEMBERINVITED = 1509,
    MEMBERENTER = 1510,
    GROUPDISMISSED = 1511,
    GROUPMEMBERMUTED = 1512,
    GROUPMEMBERCANCELMUTED = 1513,
    GROUPMUTED = 1514,
    GROUPCANCELMUTED = 1515,
    GROUPMEMBERINFOUPDATED = 1516,
    BURNMESSAGECHANGE = 1701,
}

export enum Sessioninterface {
    Single = 1,
    Group = 2,
    SuperGroup = 3,
    Notification = 4,
}

export interface NotificationElem {
    detail: string;
    defaultTips: string;
};

export interface AtElem {
    text: string;
    atUserList: string[];
    atUsersInfo?: AtUsersInfoItem[];
    quoteMessage?: string;
    isAtSelf?: boolean;
};

export interface CustomElem {
    data: string;
    description: string;
    extension: string;
};

export interface FileElem {
    filePath: string;
    uuid: string;
    sourceUrl: string;
    fileName: string;
    fileSize: number;
};

export interface FaceElem {
    index: number;
    data: string;
};

export interface LocationElem {
    description: string;
    longitude: number;
    latitude: number;
};

export interface MergeElem {
    title: string;
    abstractList: string[];
    multiMessage: MessageItem[];
};

export interface OfflinePush {
    title: string;
    desc: string;
    ex: string;
    iOSPushSound: string;
    iOSBadgeCount: boolean;
};

export interface PictureElem {
    sourcePath: string;
    sourcePicture: Picture;
    bigPicture: Picture;
    snapshotPicture: Picture;
};

export interface AttachedInfoElem {
    groupHasReadInfo: GroupHasReadInfo;
    isPrivateChat: boolean;
    hasReadTime: number;
    notSenderNotificationPush: boolean;
};

export interface GroupHasReadInfo {
    hasReadCount: number;
    hasReadUserIDList: string[];
    groupMemberCount: number;
};

export interface Picture {
    uuid: string;
    type: string;
    size: number;
    width: number;
    height: number;
    url: string;
};

export interface QuoteElem {
    text: string;
    quoteMessage: MessageItem;
};

export interface SoundElem {
    uuid: string;
    soundPath: string;
    sourceUrl: string;
    dataSize: number;
    duration: number;
};

export interface VideoElem {
    videoPath: string;
    videoUUID: string;
    videoUrl: string;
    videoType: string;
    videoSize: number;
    duration: number;
    snapshotPath: string;
    snapshotUUID: string;
    snapshotSize: number;
    snapshotUrl: string;
    snapshotWidth: number;
    snapshotHeight: number;
};

export interface SearchLocalLogData {
    conversationID: string;
    conversationType: number;
    faceURL: string;
    messageCount: number;
    messageList: MessageItem[];
    showName: string;
    sendTime?: string;
    latestMsg?: string;
};

export interface GetSubDepParams {
    departmentID: string;
    offset: number;
    count: number;
};

export interface SearchInOrzParams {
    input: SearchInputType;
    offset: number;
    count: number;
};

export interface SearchInputinterface {
    keyWord: string;
    isSearchUserName: boolean;
    isSearchEnglishName: boolean;
    isSearchPosition: boolean;
    isSearchUserID: boolean;
    isSearchMobile: boolean;
    isSearchEmail: boolean;
    isSearchTelephone: boolean;
};

export interface DepartmentItem {
    attachedInfo: string;
    createTime: number;
    departmentID: string;
    departmentType: number;
    ex: string;
    faceURL: string;
    memberNum: number;
    name: string;
    order: number;
    parentID: string;
    subDepartmentNum: number;
}

export interface DepartmentMemberItem {
    userID: string;
    nickname: string;
    englishName: string;
    faceURL: string;
    gender: number;
    mobile: string;
    telephone: string;
    birth: number;
    email: string;
    departmentID: string;
    order: number;
    position: string;
    leader: number;
    status: number;
    createTime: number;
    ex: string;
    attachedInfo: string;
}

export interface DepartmentSearchResult {
    departmentList: DepartmentItem[];
    departmentMemberList: DepartmentMemberSearchItem[];
}

export interface DepartmentMemberSearchItem extends DepartmentMemberItem {
    departmentName: string;
    parentDepartmentList: {
        name: string;
        departmentID: string;
    };
}

export interface FindMessageParams {
    conversationID: string;
    clientMsgIDList: string[]
}
