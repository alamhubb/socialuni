import { VuexModule, Module, Action } from 'vuex-class-modules'
import CircleVO from '../model/community/tag/CircleVO'
import CircleTypeVO from '../model/community/tag/CircleTypeVO'
import CircleAPI from '../api/CircleAPI'
import SocialAppModule from './SocialAppModule'

@Module({ generateMutationSetters: true })
export default class SocialCircleModule extends VuexModule {
  tags: CircleVO[] = []
  tagTypes: CircleTypeVO[] = []

  @Action
  getHotCirclesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryHotCirclesAPI().then((res: any) => {
      this.tags = res.data
    })
  }

  //应该查询前20条已读和未读通知，然后有未读通知推送
  /*@Action
  getCirclesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryCirclesAPI().then((res: any) => {
      this.tags = res.data
    })
  }*/


  @Action
  /*getHotCircleTypesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryHotCircleTypesAPI().then((res: any) => {
      this.tagTypes = res.data
    })
  }*/

  @Action
  getCircleTypesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryCircleTypesAPI().then((res: any) => {
      this.tagTypes = res.data
    })
  }
}
