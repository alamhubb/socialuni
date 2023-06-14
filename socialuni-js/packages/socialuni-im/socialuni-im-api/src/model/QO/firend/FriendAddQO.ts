import SocialuniAddFriendType from '../../../constant/SocialuniAddFriendType'

export default class FriendAddQO {
    userId: string = null
    //申请消息
    applyMsg: string = null
    reqType: string = null

    constructor(userId: string, applyMsg: string, reqType: string = SocialuniAddFriendType.apply) {
        this.userId = userId;
        this.reqType = reqType;
        this.applyMsg = applyMsg;
    }
}
