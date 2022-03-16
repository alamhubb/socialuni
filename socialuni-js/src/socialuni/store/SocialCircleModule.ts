import { Action, Module, VuexModule } from 'vuex-class-modules'
import CircleAPI from '../api/CircleAPI'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'

@Module({ generateMutationSetters: true })
export default class SocialCircleModule extends VuexModule {
  circleName: string = null
  circles: SocialCircleRO[] = []
  circleTypes: CircleTypeRO[] = []
  historyCircles: CircleTypeRO [] = []

  setCircleName (circleName: string) {
    this.circleName = circleName
  }

  @Action
  getHotCirclesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return CircleAPI.queryHotCirclesAPI().then((res) => {
      this.circles = res.data
    })
  }

  //可以加一个使用过的circle列表
  get mineCirclesTop10 () {
    const showCircleNames: string[] = [null]
    if (this.circleName) {
      showCircleNames.push(this.circleName)
    }
    showCircleNames.push(...this.circles.map(item => item.name))
    //当前选中的circle
    //历史的circle
    //热门的circle
    const circleSet = new Set(showCircleNames)
    const circles = Array.from(circleSet).slice(0, 10)
    return circles
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
    })
  }
}
