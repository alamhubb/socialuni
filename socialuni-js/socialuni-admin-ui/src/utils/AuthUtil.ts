// import Cookies from 'js-cookie'
import StorageUtil from '@/utils/StorageUtil'

export default class AuthUtil {
  static tokenKey = 'vue_admin_template_token'
  static publicKey = 'public_key'

  /* static getToken() {
    return Cookies.get(AuthUtil.tokenKey)
  }

  static setToken(token) {
    return Cookies.set(AuthUtil.tokenKey, token)
  }

  static removeToken() {
    return Cookies.remove(AuthUtil.tokenKey)
  }*/

  // 获取公钥
  static getPublicKey() {
    return StorageUtil.getObj(AuthUtil.publicKey)
  }
  // 设置公钥
  static setPublicKey(key) {
    return StorageUtil.setObj(AuthUtil.publicKey, key)
  }
}

