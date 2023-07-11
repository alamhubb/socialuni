import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/SocialuniImPagePathInterface";

const module = await PlatformModuleLoadUtil.dynamicImport('@socialuni/socialuni-im-view', 'src/constant/SocialuniImPagePath.ts')
const imPagePath: SocialuniImPagePathInterface = module.default

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
