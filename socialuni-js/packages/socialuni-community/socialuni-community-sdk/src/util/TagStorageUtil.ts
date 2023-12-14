import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames(mineHistoryCircleNames: string[]) {
        QingAppUtil.StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, mineHistoryCircleNames)
    }

    static getTagNames(): string[] {
        return QingAppUtil.StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
