import ImPagePath from "../constant/ImPagePath";

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
