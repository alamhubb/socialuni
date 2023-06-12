import {socialuniTagModule} from "../store/SocialTagModule";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class TagStorageUtil {
    static getMineHistoryTagNamesKey = 'mineTagNamesKey'

    static saveTagNames() {
        SocialuniAppUtil.StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialuniTagModule.mineHistoryTagNames)
    }

    static getTagNames(): string[] {
        return SocialuniAppUtil.StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
    }
}
