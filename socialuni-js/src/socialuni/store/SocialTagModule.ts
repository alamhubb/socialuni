import {Action, Module, VuexModule} from 'vuex-class-modules'
import TagVO from '../model/community/tag/TagVO'
import TagTypeVO from '../model/community/tag/TagTypeVO'
import TagAPI from '../api/socialuni/TagAPI'
import TagStorageUtil from '@/socialuni/constant/TagStorageUtil'

@Module({generateMutationSetters: true})
export default class SocialTagModule extends VuexModule {
  tags: TagVO[] = []
  tagTypes: TagTypeVO[] = []
  checkedTags: TagVO[] = []
  selectTag: TagVO = null
  selectTagName: string = null
  //最多存4个
  mineHistoryTagNames: string[] = TagStorageUtil.getTagNames()

  get selectTagNames() {
    if (this.selectTagName) {
      return [this.selectTagName]
    }
    return []
  }

  //可以加一个使用过的circle列表
  get mineTagsTop10() {
    const selectTagNames: string[] = [null]
    if (this.selectTag) {
      selectTagNames.push(this.selectTagName)
    }
    selectTagNames.push(...this.mineHistoryTagNames)
    selectTagNames.push(...this.tags.map(item => item.name))
    //当前选中的Tag
    //历史的Tag
    //热门的Tag
    const tagSet = new Set(selectTagNames)
    const tags = Array.from(tagSet).slice(0, 10)
    return tags
  }

  @Action
  getHotTagsAction() {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryHotTagsAPI().then((res: any) => {
      this.tags = res.data
    })
  }

  //应该查询前20条已读和未读通知，然后有未读通知推送
  @Action
  getTagsAction() {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryTagsAPI().then((res: any) => {
      this.tags = res.data
    })
  }

  @Action
  getHotTagTypesAction() {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryHotTagTypesAPI().then((res: any) => {
      this.tagTypes = res.data
    })
  }

  @Action
  getTagTypesAction() {
    if (!this.tagTypes[1] || !this.tagTypes[1].tags || !this.tagTypes[1].tags.length) {
      // 查询前20条，未读优先，如果没有未读，就是按时间排序
      return TagAPI.queryTagTypesAPI().then((res: any) => {
        this.tagTypes = res.data
      })
    }
  }

  setSelectTagName(selectTagName: string) {
    this.selectTagName = selectTagName
    this.setMineHistoryTagNames(selectTagName)
  }

  get selectTagIds(): number[] {
    if (this.selectTag) {
      return [this.selectTag.id]
    } else {
      return []
    }
  }

  setMineHistoryTagNames(tagName: string) {
    if (tagName) {
      this.mineHistoryTagNames.unshift(tagName)
      this.mineHistoryTagNames = this.mineHistoryTagNames.slice(0, 4)
      TagStorageUtil.saveTagNames()
    }
  }
}
