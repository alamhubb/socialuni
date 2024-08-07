/*
import SocialAuthType from '@/socialuni/constant/SocialAuthType'
import DevAccountAPI from '@/socialuni/api/DevAccountAPI'
import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import SocialuniAuthQO from '@/socialuni/model/openData/SocialuniAuthQO'
import {socialOAuthModule, socialuniSystemModule} from '@/socialuni/store/index'





class SocialOAuthModule {
    //三方授权时携带的参数
    threeAppId = null
    threeAuthType = null
    threeDevAccount: DevAccountRO = null

    //是否为三方授权
    get isThreeAuth() {
        return !!this.threeAuthType
    }

    get isAuthUser() {
        return this.isThreeAuth && this.threeAuthType === SocialAuthType.user
    }

    get isAuthPhone() {
        return this.isThreeAuth && this.threeAuthType === SocialAuthType.phone
    }


    queryDevAccountAction() {
        DevAccountAPI.queryDevAccountAPI(socialOAuthModule.threeAppId, socialuniSystemModule.mpPlatform).then(res => {
            this.threeDevAccount = res.data
        })
    }

    setThreeAuthInfo(params) {
        socialOAuthModule.threeAuthType = null
        if (params && params.referrerInfo) {
            const info = params.referrerInfo
            if (info.appId) {
                //获取三方的appid
                socialOAuthModule.threeAppId = info.appId
                //如果有跳转信息
                const extraData: SocialuniAuthQO = info.extraData
                if (extraData) {
                    socialOAuthModule.threeAuthType = extraData.authType
                    socialOAuthModule.queryDevAccountAction()
                    if (!extraData.authType) {
                        QingAppUtil.AlertUtil.hint('授权类型错误')
                    }
                    //支持非授权跳转
                    /!*if (!appModule.threeSecretKey) {
                      constant result: ResultVO<any> = new ResultVO<any>()
                      result.errorCode = ErrorCode.business
                      result.errorMsg = '社交联盟密钥错误'
                      result.success = false
                      QingAppUtil.UniUtil.showLoading('密钥错误，返回中...')
                      uni.navigateBackMiniProgram({ extraData: result })
                    }*!/
                }
            }
        }
    }
}
*/
