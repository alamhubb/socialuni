import StorageUtil from "./StorageUtil";
import {socialTagModule} from "../store/store";

export default class TagStorageUtil {
  static getMineHistoryTagNamesKey = 'mineTagNamesKey'

  static saveTagNames () {
    StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialTagModule.mineHistoryTagNames)
  }

  static getTagNames (): string[] {
    return StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
  }
}
