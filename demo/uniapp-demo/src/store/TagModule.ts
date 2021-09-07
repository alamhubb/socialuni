import { VuexModule, Module, Action } from 'vuex-class-modules'
import TagVO from '@/model/tag/TagVO'
import TagTypeVO from '@/model/tag/TagTypeVO'
import TagAPI from '/socialuni/api/TagAPI'

@Module({ generateMutationSetters: true })
export default class TagModule extends VuexModule {
  tags: TagVO[] = []
  tagTypes: TagTypeVO[] = []

  @Action
  getHotTagsAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryHotTagsAPI().then((res: any) => {
      this.tags = res.data
    })
  }

  //应该查询前20条已读和未读通知，然后有未读通知推送
  @Action
  getTagsAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryTagsAPI().then((res: any) => {
      this.tags = res.data
    })
  }


  @Action
  getHotTagTypesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryHotTagTypesAPI().then((res: any) => {
      this.tagTypes = res.data
    })
  }

  @Action
  getTagTypesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return TagAPI.queryTagTypesAPI().then((res: any) => {
      this.tagTypes = res.data
    })
  }
}
