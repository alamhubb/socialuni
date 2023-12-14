import TagVO from "socialuni-api-base/src/model/community/tag/TagVO";
import Constants from "socialuni-constant/constant/Constant";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

const initTag = new TagVO()
initTag.name = Constants.initTagName
initTag.id = 0

export default class TagUtil {
  static readonly initTag: TagVO = initTag
  static readonly initTagAry: TagVO[] = [initTag]

  private static readonly historyTagsKey: string = 'historyTags'

  static getStorageHistoryTags (): TagVO[] {
    const tags: TagVO[] = QingAppUtil.StorageUtil.getObj(this.historyTagsKey)
    return tags || []
  }

  static setStorageHistoryTags (tags: TagVO[]) {
    QingAppUtil.StorageUtil.setObj(this.historyTagsKey, tags)
  }
}
