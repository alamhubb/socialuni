import http from '@/plugins/http'

export default class NotifyAPI {
  static queryNotifiesAPI () {
    return http.post('notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI () {
    return http.post('notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
