import RouterUtil from "socialuni-util/src/util/RouterUtil";
import ImPagePath from "../constant/ImPagePath";
import UserPagePath from "socialuni/src/constant/UserPagePath";

export default class ImPageUtil {

    static toMessagePageByChatId(receiveId: string) {
        RouterUtil.navigateTo(ImPagePath.message + '?chatId=' + receiveId)
    }

    static toChatFriend() {
        RouterUtil.navigateTo(ImPagePath.friend)
    }

    static toFriendApply() {
        RouterUtil.navigateTo(ImPagePath.friendApply)
    }

}
