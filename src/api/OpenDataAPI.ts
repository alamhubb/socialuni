import http from '@/plugins/http'
import AppUpdateVO from '@/model/app/AppUpdateVO'
import AppUpdateResultVO from '@/model/app/AppUpdateResultVO'
import FrontErrorLogVO from '@/model/app/FrontErrorLogVO'
import JsonUtil from '@/utils/JsonUtil'
import { AuthUserInfoRequestVO } from '@/model/openData/AuthUserInfoRequestVO'
import { systemModule } from '@/plugins/store'
import { AuthUserInfoResultVO } from '@/model/openData/AuthUserInfoResultVO'

export default class OpenDataAPI {
  static authUserInfoAPI () {
    const requestVO: AuthUserInfoRequestVO = {
      // threeSecretKey: appModule.threeSecretKey,
      // threeUserId: appModule.threeUserId,
      // providerAppId: appModule.threeProviderAppId,
      providerType: systemModule.provider
    }
    return http.post<AuthUserInfoResultVO>('user/authUserInfo', requestVO)
  }

  static queryAppInitDataReadyAPI () {
    return http.post('app/queryAppInitDataReady')
  }

  static checkUpdateAPI (versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return http.post<AppUpdateResultVO>('app/checkUpdate', appUpdate)
  }

  static sendErrorLog (uri: string, detail: string, params: any, errorMsg: any) {
    return http.post('app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg)))
  }
}
