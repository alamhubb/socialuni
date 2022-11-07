import StorageUtil from "../../../socialuni-use/src/utils/StorageUtil";
import {socialTagModule} from "socialuni-sdk/src/store/store";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames() {
        StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialTagModule.mineHistoryTagNames)
    }

    static getTagNames(): string[] {
        return StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
