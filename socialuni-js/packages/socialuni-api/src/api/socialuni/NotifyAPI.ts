import request from '../../../socialuni-common-api/src/request/request';

export default class NotifyAPI {
  static queryNotifiesAPI() {
    return request.get('socialuni/notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI() {
    return request.get('socialuni/notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
