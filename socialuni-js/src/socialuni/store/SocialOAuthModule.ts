import { Action, Module, VuexModule } from 'vuex-class-modules'
import SocialAuthType from '@/socialuni/const/SocialAuthType'
import DevAccountAPI from '@/socialuni/api/DevAccountAPI'
import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import Alert from '@/socialuni/utils/Alert'
import LoginProvider from '@/socialuni/const/LoginProvider'
import SocialuniAuthQO from '@/socialuni/model/openData/SocialuniAuthQO'
import { socialOAuthModule } from '@/socialuni/store/index'

@Module({ generateMutationSetters: true })
export default class SocialOAuthModule extends VuexModule {
  //三方授权时携带的参数
  threeSecretKey = ''
  threeAppId = null
  threeAuthType = null
  // threeUserId = '123'
  // threeAuthType = ''
  // threeProviderType = ''
  threeDevAccount: DevAccountRO = null

  //是否为三方授权
  get isThreeAuth () {
    return !!this.threeAuthType
  }

  get isAuthUser () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.user
  }

  get isAuthPhone () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.phone
  }

  @Action
  queryDevAccountAction () {
    console.log(socialOAuthModule.threeAppId)
    DevAccountAPI.queryDevAccountAPI(socialOAuthModule.threeAppId, LoginProvider.wx).then(res => {
      this.threeDevAccount = res.data
    })
  }

  setThreeAuthInfo (params) {
    if (params && params.referrerInfo) {
      const info = params.referrerInfo
      console.log(info)
      if (info.appId) {
        //获取三方的appid
        socialOAuthModule.threeAppId = info.appId
        //如果有跳转信息
        const extraData: SocialuniAuthQO = info.extraData
        if (extraData) {
          // socialOAuthModule.threeSecretKey = extraData.appSecretKey
          // appModule.threeUserId = extraData.appUserId
          socialOAuthModule.threeAuthType = extraData.authType
          socialOAuthModule.queryDevAccountAction()
          if (!extraData.authType) {
            /* const result: ResultRO<any> = new ResultRO<any>()
             result.errorCode = ErrorCode.business
             result.errorMsg = '社交联盟密钥错误'
             result.success = false*/
            Alert.hint('授权类型错误')
            // uni.navigateBackMiniProgram({ extraData: result })
          }
          //支持非授权跳转
          /*if (!appModule.threeSecretKey) {
            const result: ResultVO<any> = new ResultVO<any>()
            result.errorCode = ErrorCode.business
            result.errorMsg = '社交联盟密钥错误'
            result.success = false
            UniUtil.showLoading('密钥错误，返回中...')
            uni.navigateBackMiniProgram({ extraData: result })
          }*/
        }
      }
    }
  }
}
