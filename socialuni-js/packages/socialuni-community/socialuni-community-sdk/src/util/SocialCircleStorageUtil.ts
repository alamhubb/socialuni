import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

export default class SocialCircleStorageUtil {
    static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

    static saveCircleNames() {
        QingAppUtil.StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getCircleNames(): string[] {
        return QingAppUtil.StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
    }
}
