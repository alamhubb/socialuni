import socialHttp from '@/socialuni/http/socialHttp'

export default class NotifyAPI {
  static queryNotifiesAPI () {
    return socialHttp.post('notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI () {
    return socialHttp.post('notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
