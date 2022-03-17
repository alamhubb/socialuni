import ObjectUtil from '@/socialuni/utils/ObjectUtil'
import { socialTagModule } from '@/socialuni/store'
import StorageUtil from '@/socialuni/utils/StorageUtil'

export default class TagStorageUtil {
  static getMineHistoryTagNamesKey = 'mineTagsKey'

  static saveTagNames () {
    StorageUtil.set(TagStorageUtil.getMineHistoryTagNamesKey, ObjectUtil.toJson(socialTagModule.mineHistoryTagNames))
  }

  static getTagNames (): string[] {
    const data = StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey)
    if (data) {
      return ObjectUtil.toParse(StorageUtil.get(TagStorageUtil.getMineHistoryTagNamesKey))
    }
    return []
  }
}
