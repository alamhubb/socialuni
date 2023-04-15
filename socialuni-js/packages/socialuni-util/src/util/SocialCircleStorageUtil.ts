import {socialuniTagModule} from "socialuni-community/src/store/SocialTagModule";
import StorageUtil from "socialuni-base-api/src/util/StorageUtil";

export default class SocialCircleStorageUtil {
    static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

    static saveCircleNames() {
        StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getCircleNames(): string[] {
        return StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
    }
}
