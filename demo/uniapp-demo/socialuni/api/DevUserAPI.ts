import socialHttp from '/socialuni/plugins/http/socialHttp'
import DevUserVO from '/socialuni/model/dev/DevUserVO'
import DevUserDetailQueryVO from '/socialuni/model/dev/DevUserDetailQueryVO'
import { appModule } from '/socialuni/store'

export default class DevUserAPI {
  static queryDevUserDetailAPI () {
    const queryVO: DevUserDetailQueryVO = new DevUserDetailQueryVO(appModule.threeSecretKey, appModule.threeAppId)
    return socialHttp.post<DevUserVO>('devUser/queryDevUserDetail', queryVO)
  }
}
