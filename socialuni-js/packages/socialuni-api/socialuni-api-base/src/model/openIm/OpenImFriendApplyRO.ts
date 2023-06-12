export default class OpenImFriendApplyRO {
    fromUserID: string = null
    type: string = null
    fromNickname: string = null
    fromFaceURL: string = null
    fromGender: number = null
    toUserID: string = null
    toNickname: string = null
    toFaceURL: string
    toGender: number = null
    handleResult: number = null
    reqMsg: string = null
    createTime: number = null
    handlerUserID: string
    handleMsg: string = null
    handleTime: number = null
    ex: string = null
    attachedInfo: string = null


    constructor(data: OpenImFriendApplyRO) {
        this.fromUserID = data.fromUserID;
        this.fromNickname = data.fromNickname;
        this.fromFaceURL = data.fromFaceURL;
        this.fromGender = data.fromGender;
        this.toUserID = data.toUserID;
        this.toNickname = data.toNickname;
        this.toFaceURL = data.toFaceURL;
        this.toGender = data.toGender;
        this.handleResult = data.handleResult;
        this.reqMsg = data.reqMsg;
        this.createTime = data.createTime;
        this.handlerUserID = data.handlerUserID;
        this.handleMsg = data.handleMsg;
        this.handleTime = data.handleTime;
        this.ex = data.ex;
        this.attachedInfo = data.attachedInfo;
    }
}
