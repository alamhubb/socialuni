import { Action, Module, VuexModule } from 'vuex-class-modules'
import UnreadNotifyVO from '../model/notify/UnreadNotifyVO'
import NotifyAPI from '../api/NotifyAPI'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'

//用来存储当前用户的一些信息
@Module({ generateMutationSetters: true })
export default class SocialNotifyModule extends VuexModule {
  notifies: UnreadNotifyVO [] = []

  get unreadNotifies (): UnreadNotifyVO [] {
    return this.notifies.filter(item => !item.hasRead)
  }

  addUnreadNotifies (user: CenterUserDetailRO) {
    const notify: UnreadNotifyVO = new UnreadNotifyVO()
    notify.hasRead = false
    notify.avatar = user.avatar
    notify.nickname = user.nickname
    // notify.vipFlag = user.vipFlag
    // 从列表中加入这个
    this.notifies.unshift(notify)
  }

  //应该查询前20条已读和未读通知，然后有未读通知推送
  @Action
  queryNotifiesAction () {
    // 查询前20条，未读优先，如果没有未读，就是按时间排序
    return NotifyAPI.queryNotifiesAPI().then((res: any) => {
      this.notifies = res.data
    })
  }

  @Action
  queryUnreadNotifiesAndUpdateHasReadAction () {
    this.notifies.forEach(item => {
      item.hasRead = true
    })
    // 通知后台和前台把消息改为已读
    return NotifyAPI.queryUnreadNotifiesAndUpdateHasReadAPI().then((res: any) => {
      this.notifies = res.data
    })
  }
}
