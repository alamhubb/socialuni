import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/SocialuniImPagePathInterface";

export default class ImPageUtil {
    static async getImPagePath(){
        const module = await PlatformModuleLoadUtil.dynamicImport('@socialuni/socialuni-im-view', 'src/constant/SocialuniImPagePath.ts')
        console.log(module)
        let imPagePath: SocialuniImPagePathInterface = module.default
        console.log(imPagePath)
        console.log(imPagePath.message)
        return imPagePath
    }

    static async toMessagePageByChatId(receiveId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).message + '?chatId=' + receiveId)
    }

    static async toChatFriend() {
        SocialuniAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).friend)
    }

    static async toFriendApply() {
        SocialuniAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).friendApply)
    }

}
