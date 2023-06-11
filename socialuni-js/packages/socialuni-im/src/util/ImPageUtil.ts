import ImPagePath from "../constant/ImPagePath";
import SocialuniAppUtil from "socialuni-app/src/util/SocialuniAppUtil";

export default class ImPageUtil {

    static toMessagePageByChatId(receiveId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(ImPagePath.message + '?chatId=' + receiveId)
    }

    static toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo(ImPagePath.friend)
    }

    static toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo(ImPagePath.friendApply)
    }

}
