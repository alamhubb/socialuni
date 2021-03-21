import { VuexModule, Module, Action } from 'vuex-class-modules'
import UserVO from '@/model/user/UserVO'

import UserAPI from '@/api/UserAPI'
import { userModule } from '@/plugins/store/index'
import UserStorageUtil from '@/utils/UserStorageUtil'
import Alert from '@/utils/Alert'
import Toast from '@/utils/Toast'
import UserService from '@/service/UserService'
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import BindPhoneNumAPI from '@/plugins/social/api/BindPhoneNumAPI'

//用来存储当前用户的一些信息
@Module({ generateMutationSetters: true })
export default class UserModule extends VuexModule {
  user: UserVO = UserStorageUtil.get() || null

  get hasPhoneNum () {
    return this.user && this.user.phoneNum
  }

  setUser (user: UserVO) {
    this.user = user
    UserStorageUtil.set(user)
  }

  get hasUser (): boolean {
    return !!this.user
  }

  loginOut () {
    return Alert.confirm('是否退出登录').then(() => {
      UserService.clearUserInfoCom()
      Toast.toast('用户退出')
    })
  }

  @Action
  destroyAccount () {
    return Alert.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
      UserAPI.destroyAccountAPI().then(() => {
        UserService.clearUserInfoCom()
        Toast.toast('注销成功')
      })
    })
  }

  /**
   * 调用后台仅获取user信息
   */
  @Action
  getMineUserAction () {
    return UserAPI.getMineUserInfoAPI().then((res: any) => {
      userModule.setUser(res.data)
    })
  }

  //跳转清池绑定手机号
  @Action
  bindPhoneNumAction (loginData: ProviderUserVO) {
    return BindPhoneNumAPI.bindPhoneNumByWxAPI(loginData).then((res) => {
      userModule.setUser(res.data)
      Toast.toast('绑定手机号成功')
      return res.data
    })
  }
}
