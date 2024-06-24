import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import PlatformModuleLoadUtil from "qing-compat-js/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "../constant/SocialuniImPagePathInterface";

export default class ImPageUtil {
    static async getImPagePath(){
        const module = await PlatformModuleLoadUtil.dynamicImport('socialuni-im', '-sdk/src/constant/SocialuniImPagePath.ts')
        let imPagePath: SocialuniImPagePathInterface = module.default
        return imPagePath
    }

    static async toMessagePageByChatId(receiveId: string) {
        QingAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).message + '?chatId=' + receiveId)
    }

    static async toChatFriend() {
        QingAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).friend)
    }

    static async toFriendApply() {
        QingAppUtil.RouterUtil.navigateTo((await ImPageUtil.getImPagePath()).friendApply)
    }

}
