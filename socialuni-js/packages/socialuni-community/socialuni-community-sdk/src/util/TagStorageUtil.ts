import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames(mineHistoryCircleNames: string[]) {
        SocialuniAppUtil.StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, mineHistoryCircleNames)
    }

    static getTagNames(): string[] {
        return SocialuniAppUtil.StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
