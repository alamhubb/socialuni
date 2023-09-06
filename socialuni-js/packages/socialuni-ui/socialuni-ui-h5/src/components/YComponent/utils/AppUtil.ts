import CookieUtil from "@/components/YComponent/utils/CookieUtil";

const tokenKey = 'access_token'
const usernameKey = 'userName'
const ClusterId = 'cluster-id'

export default class AppUtil {
  static $onGlobalStateChange = null
  static $setGlobalState = null
  static $utils = null
  static $publicPath = null
  static $parentRouter = null
  static appCode = process.env.VUE_APP_CODE || null
  static readonly appName = process.env.VUE_APP_NAME
  static readonly baseUrl = process.env.VUE_APP_BASE_API
  // @ts-ignore
  static readonly isQiankun = !!window.__POWERED_BY_QIANKUN__
  static readonly isProd = process.env.NODE_ENV === 'production'

  static getSystemRootPath() {
    if (this.isQiankun) {
      const menus = JSON.parse(sessionStorage.getItem('masterMenuList'))
      if (menus) {
        for (const item of menus.appList) {
          if (item.code === AppUtil.appCode) {
            const arr = JSON.parse(item.deployUrl)
            const clusterId = parseInt(AppUtil.getClusterId())
            for (const j of arr) {
              if (j.clusterId === clusterId) {
                // config.baseURL = j.url
                return `${j.url}`
              }
            }
          }
        }
      }
    }
    return '/'
  }

  static getBaseUrl(config: { baseURL: string }) {
    if (this.isQiankun) {
      const menus = JSON.parse(sessionStorage.getItem('masterMenuList'))
      if (menus) {
        for (const item of menus.appList) {
          if (item.code === AppUtil.appCode) {
            const arr = JSON.parse(item.deployUrl)
            const clusterId = parseInt(AppUtil.getClusterId())
            for (const j of arr) {
              if (j.clusterId === clusterId) {
                config.baseURL = `/${j.clusterId}_${item.code}/`
              }
            }
          }
        }
      }
    }
  }

  static getAppendTokenStr() {
    if (this.isQiankun) {
      const token = this.getToken()
      return `&token=${token}`
    }
    return ''
  }

  static getTokenStr() {
    if (this.isQiankun) {
      const token = this.getToken()
      return `?token=${token}`
    }
    return ''
  }

  static getToken() {
    return CookieUtil.getObj(tokenKey)
  }

  static getUsername() {
    if (AppUtil.isQiankun) {
      return sessionStorage.getItem(usernameKey)
    } else {
      return CookieUtil.getObj(tokenKey)
    }
  }

  static setToken(token: string) {
    console.trace(token)
    return CookieUtil.setObj(tokenKey, token)
    //@ts-ignore
    // if (store.state.app && store.state.app.enableHttps) {
    //   return CookieUtil.set(tokenKey, token)
    //   // return CookieUtil.set(TokenKey, token, { secure: true, SameSite: 'None' })
    // } else {
    //   return CookieUtil.set(tokenKey, token)
    // }
  }

  static removeQiankunToken() {
    return CookieUtil.remove(tokenKey)
  }

  static getQiankunMenus() {
    return JSON.parse(sessionStorage.getItem(this.appName + '-menu'))
  }

  static setQiankunMenus(menu) {
    return sessionStorage.setItem(this.appName + '-menu', JSON.stringify(menu))
  }

  static getClusterId() {
    return CookieUtil.getObj(ClusterId)
  }
}
