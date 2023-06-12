import request from '../../request/request';

export default class NotifyAPI {
  static queryNotifiesAPI() {
    return socialuniCommunityRequest.get('socialuni/notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI() {
    return socialuniCommunityRequest.get('socialuni/notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
