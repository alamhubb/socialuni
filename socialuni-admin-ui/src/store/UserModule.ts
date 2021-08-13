import { VuexModule, Module, Action } from 'vuex-class-modules'
import LoginAPI from '@/api/LoginAPI'
import StorageUtil from '@/utils/StorageUtil'
import router from '@/plugins/router/router'
import { Message } from 'element-ui'
import AppConst from '@/constants/AppConst'
import LoginUserVO from '@/model/base/LoginUserVO'
import ToastUtil from '@/utils/ToastUtil'
import TokenUtil from '@/utils/TokenUtil'
import UserAPI from '@/api/UserAPI'

@Module({ generateMutationSetters: true })
export default class UserModule extends VuexModule {
  user: LoginUserVO = StorageUtil.getObj(AppConst.loginUser) || null

  setUser(user: LoginUserVO) {
    this.user = user
    if (user) {
      StorageUtil.setObj(AppConst.loginUser, user)
    } else {
      StorageUtil.remove(AppConst.loginUser)
    }
  }

  @Action
  userLogin(user: LoginUserVO) {
    return LoginAPI.loginAPI(user).then((res: any) => {
      TokenUtil.set(res.token)
      this.setUser(res)
      // this.addPermission()
      ToastUtil.success('登录成功')
    })
  }

  @Action
  getUserAction() {
    const token = TokenUtil.get()
    if (token) {
      return UserAPI.getUserAPI().then((res: any) => {
        this.setUser(res.data)
        return res.data
      })
    }
  }

  @Action
  userLoginOut() {
    this.clearUserInfo()
    Message.success('登出成功')
    // 删除管理员权限
    // menuList.splice(0, menuList.length, ...frontRoutes)
    // router.addRoutes(router.options.routes)
    router.push('/login')
  }

  clearUserInfo() {
    this.setUser(null)
    TokenUtil.remove()
  }

  /* addPermission() {
    const user = userModule.user
    if (user && user.loginPermission === 2) {
      // menuList.push(...backRoutes)
    }
    if (router) {
      router.addRoutes(router.options.routes)
    }
  }*/
}
