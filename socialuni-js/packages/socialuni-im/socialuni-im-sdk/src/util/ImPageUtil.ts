import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/SocialuniImPagePathInterface";

const module = await PlatformModuleLoadUtil.dynamicImport('@socialuni/socialuni-im-view', 'src/constant/SocialuniImPagePath.ts')
console.log(module)
let imPagePath: SocialuniImPagePathInterface = module.default

export default class ImPageUtil {
    static async toMessagePageByChatId(receiveId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.message + '?chatId=' + receiveId)
    }

    static async toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friend)
    }

    static async toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo(imPagePath.friendApply)
    }

}
