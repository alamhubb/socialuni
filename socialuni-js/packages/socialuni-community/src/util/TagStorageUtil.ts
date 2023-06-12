import {socialuniTagModule} from "../store/SocialTagModule";
import StorageUtil from "socialuni-util/src/util/StorageUtil";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames() {
        StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getTagNames(): string[] {
        return StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
