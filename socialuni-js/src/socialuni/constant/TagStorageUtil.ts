import { socialTagModule } from '@/socialuni/store'
import StorageUtil from '@/socialuni/utils/StorageUtil'

export default class TagStorageUtil {
  static getMineHistoryTagNamesKey = 'mineTagNamesKey'

  static saveTagNames () {
    StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, socialTagModule.mineHistoryTagNames)
  }

  static getTagNames (): string[] {
    return StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey) || []
  }
}
