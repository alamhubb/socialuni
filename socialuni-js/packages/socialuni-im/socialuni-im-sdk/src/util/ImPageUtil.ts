import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {SocialuniImPagePathInterface} from "../constant/ImPagePath";
import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";

const modules = import.meta.globEager('../../../socialuni-im-*-sdk/src/constant/SocialuniImPagePath.ts')

const imPagePath:SocialuniImPagePathInterface = PlatformModuleLoadUtil.getModule(modules)

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
