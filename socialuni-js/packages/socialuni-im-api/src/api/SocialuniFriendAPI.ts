import FriendAddQO from "../model/firend/FriendAddQO";
import socialuniImRequest from "./socialuniImRequest";

export default class SocialuniFriendAPI {
    static addFriend(friendAddQO: FriendAddQO) {
        console.log('chufa')
        return socialuniImRequest.get<string>('socialuni/friend/addFriend/' + friendAddQO.userId)
    }
}
