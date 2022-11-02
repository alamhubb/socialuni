import StorageUtil from "./StorageUtil";
import {socialTagModule} from "../store/store";

export default class SocialCircleStorageUtil {
  static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

  static saveCircleNames () {
    StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialTagModule.mineHistoryTagNames)
  }

  static getCircleNames (): string[] {
    return StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
  }
}
