import socialHttp from '@/socialuni/http/socialHttp'
import DevUserVO from '@/model/dev/DevUserVO'
import DevUserDetailQueryVO from '@/model/dev/DevUserDetailQueryVO'
import { appModule } from '@/store'

export default class DevUserAPI {
  static queryDevUserDetailAPI () {
    const queryVO: DevUserDetailQueryVO = new DevUserDetailQueryVO(appModule.threeSecretKey, appModule.threeAppId)
    return socialHttp.post<DevUserVO>('devUser/queryDevUserDetail', queryVO)
  }
}
