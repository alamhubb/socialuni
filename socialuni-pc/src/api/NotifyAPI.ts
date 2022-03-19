import request from '../plugins/http/request'

export default class NotifyAPI {
  static queryNotifiesAPI () {
    return request.post('notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI () {
    return request.post('notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
