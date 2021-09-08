import socialHttp from '../plugins/http/socialHttp'
import DevUserVO from '../model/dev/DevUserVO'
import DevUserDetailQueryVO from '../model/dev/DevUserDetailQueryVO'
import { socialAppModule } from '../store'

export default class DevUserAPI {
  static queryDevUserDetailAPI () {
    const queryVO: DevUserDetailQueryVO = new DevUserDetailQueryVO(socialAppModule.threeSecretKey, socialAppModule.threeAppId)
    return socialHttp.post<DevUserVO>('devUser/queryDevUserDetail', queryVO)
  }
}
