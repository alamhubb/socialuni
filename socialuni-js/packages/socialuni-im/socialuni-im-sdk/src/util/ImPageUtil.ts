import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import PlatformAutoLoadUtil from "socialuni-native-util/src/util/PlatformAutoLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/ImPagePath";

const modules = import.meta.globEager('../../../socialuni-im-*-sdk/src/constant/SocialuniImPagePath.ts')

const imPagePath:SocialuniImPagePathInterface = PlatformAutoLoadUtil.getNativeUtil(modules)

console.log(imPagePath)
export default class ImPageUtil {

    static toMessagePageByChatId(receiveId: string) {
        console.log(imPagePath)
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.message + '?chatId=' + receiveId)
    }

    static toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friend)
    }

    static toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friendApply)
    }

}
