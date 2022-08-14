import request from '../../plugins/http/request'

export default class NotifyAPI {
  static queryNotifiesAPI() {
    return request.post('socialuni/notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI() {
    return request.post('socialuni/notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
