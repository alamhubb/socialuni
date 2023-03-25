import FriendAddQO from "../model/QO/firend/FriendAddQO";
import socialuniImRequest from "./socialuniImRequest";
import SocialuniFriendApplyUserRO from "../model/RO/SocialuniFriendApplyUserRO";

export default class SocialuniFriendAPI {
    static addFriend(friendAddQO: FriendAddQO) {
        return socialuniImRequest.post<void>('socialuni/friend/addFriend', friendAddQO)
    }

    static queryFriendApplyList() {
        return socialuniImRequest.get<SocialuniFriendApplyUserRO[]>('socialuni/friend/queryFriendApplyList')
    }
}
