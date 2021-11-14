import { Action, Module, VuexModule } from 'vuex-class-modules'
import CircleAPI from '../api/CircleAPI'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'

@Module({ generateMutationSetters: true })
export default class SocialCircleModule extends VuexModule {
  circles: SocialCircleRO[] = []
  circleTypes: CircleTypeRO[] = []

  @Action
  getHotCirclesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryHotCirclesAPI().then((res) => {
      this.circles = res.data
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
    return CircleAPI.queryCircleTypesAPI().then((res) => {
      this.circleTypes = res.data
      console.log(this.circleTypes)
    })
  }
}
