import { VuexModule, Module } from 'vuex-class-modules'
import UserVO from '@/model/user/UserVO'

@Module({ generateMutationSetters: true })
export default class UserModule extends VuexModule {
  user: UserVO = null

  get hasPhoneNum () {
    return this.user && this.user.phoneNum
  }
}
