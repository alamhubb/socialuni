import router from '@/router/router'

export default class PageUtil {
  static toHome() {
    router.push('/')
  }

  static toLogin() {
    router.push('/login')
  }
}
