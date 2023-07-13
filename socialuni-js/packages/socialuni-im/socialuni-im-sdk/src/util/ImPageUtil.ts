import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniImTopLevelAwaitUtil from "./SocialuniImTopLevelAwaitUtil";

export default class ImPageUtil {
    static async toMessagePageByChatId(receiveId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo((await SocialuniImTopLevelAwaitUtil.getSocialuniImPagePath()).message + '?chatId=' + receiveId)
    }

    static async toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo((await SocialuniImTopLevelAwaitUtil.getSocialuniImPagePath()).friend)
    }

    static async toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo((await SocialuniImTopLevelAwaitUtil.getSocialuniImPagePath()).friendApply)
    }

}
