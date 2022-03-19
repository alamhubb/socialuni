import { socialTagModule } from '@/socialuni/store'
import StorageUtil from '@/socialuni/utils/StorageUtil'

export default class SocialCircleStorageUtil {
  static getMineCircleNamesKeyKey = 'mineCircleNamesKey'

  static saveCircleNames () {
    StorageUtil.set(SocialCircleStorageUtil.getMineCircleNamesKeyKey, socialTagModule.mineHistoryTagNames)
  }

  static getCircleNames (): string[] {
    return StorageUtil.get(SocialCircleStorageUtil.getMineCircleNamesKeyKey) || []
  }
}
