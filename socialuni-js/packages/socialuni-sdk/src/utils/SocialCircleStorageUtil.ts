import StorageUtil from "socialuni-base/src/utils/StorageUtil";
import {socialTagModule} from "socialuni-sdk/src/store/store";

export default class SocialCircleStorageUtil {
    static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

    static saveCircleNames() {
        StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialTagModule.mineHistoryTagNames)
    }

    static getCircleNames(): string[] {
        return StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
    }
}
