import StorageUtil from "socialuni/src/util/StorageUtil";
import {socialuniTagModule} from "../store/SocialTagModule";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames() {
        StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getTagNames(): string[] {
        return StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
