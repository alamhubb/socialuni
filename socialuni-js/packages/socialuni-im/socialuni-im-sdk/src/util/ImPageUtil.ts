import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import PlatformAutoLoadUtil from "socialuni-native-util/src/util/PlatformAutoLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/ImPagePath";

const modules = import.meta.globEager('../../../socialuni-im-*/src/constant/SocialuniImPagePath.ts')

const imPagePath:SocialuniImPagePathInterface = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class ImPageUtil {

    static toMessagePageByChatId(receiveId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.message + '?chatId=' + receiveId)
    }

    static toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friend)
    }

    static toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friendApply)
    }

}
