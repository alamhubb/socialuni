import ProviderUserVO from '@/model/ProviderUserVO'
import UniUtil from '@/utils/UniUtil'
import { systemModule } from '@/store'
import ProviderType, { Provider } from '@/const/ProviderType'
import JsonUtils from '@/utils/JsonUtil'
import PlatformType from '@/const/PlatformType'
import Constants from '@/const/Constant'
import MsgUtil from '@/utils/MsgUtil'
import Alert from '../../utils/Alert'

export default class LoginService {
  static getLoginData (provider: Provider) {
    return UniUtil.login(provider).then((loginRes) => {
      const loginData: ProviderUserVO = new ProviderUserVO()
      loginData.provider = provider
      loginData.platform = systemModule.platform
      if (PlatformType.mp === loginData.platform) {
        loginData.code = loginRes.code
      } else {
        //数据本身为字符串，需要解析
        loginData.accessToken = JsonUtils.deepClone(loginRes.authResult).access_token
      }
      return loginData
    })
  }
}
