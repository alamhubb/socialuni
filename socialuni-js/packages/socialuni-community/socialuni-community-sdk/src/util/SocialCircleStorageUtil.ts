import {socialuniTagModule} from "@socialuni/socialuni-community-sdk/src/store/SocialTagModule";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class SocialCircleStorageUtil {
    static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

    static saveCircleNames() {
        SocialuniAppUtil.StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getCircleNames(): string[] {
        return SocialuniAppUtil.StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
    }
}
