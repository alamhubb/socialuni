import SocialuniUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniUserRO";

export default class SocialuniFriendApplyUserRO extends SocialuniUserRO{
    //申请消息
    friendApplyStatus: string = null
    applyMsg: string = null
    createTime: Date = null
}
