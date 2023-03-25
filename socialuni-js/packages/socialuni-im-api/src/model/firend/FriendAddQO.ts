export default class FriendAddQO {
    userId: string = null
    //申请消息
    applyMsg: string = null


    constructor(userId: string, applyMsg: string) {
        this.userId = userId;
        this.applyMsg = applyMsg;
    }
}
