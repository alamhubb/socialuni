import http from '@/plugins/http'
import DevUserVO from '@/model/dev/DevUserVO'
import DevUserDetailQueryVO from '@/model/dev/DevUserDetailQueryVO'
import { appModule } from '@/plugins/store'

export default class DevUserAPI {
  static queryDevUserDetailAPI () {
    const queryVO: DevUserDetailQueryVO = new DevUserDetailQueryVO(appModule.threeSecretKey, appModule.threeAppId)
    return http.post<DevUserVO>('devUser/queryDevUserDetail', queryVO)
  }
}
