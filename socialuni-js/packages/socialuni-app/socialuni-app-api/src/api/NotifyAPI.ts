import socialuniAppRequest from "../socialuniAppRequest";

export default class NotifyAPI {
  static queryNotifiesAPI() {
    return socialuniAppRequest.get('socialuni/notify/queryNotifies')
  }

  static queryUnreadNotifiesAndUpdateHasReadAPI() {
    return socialuniAppRequest.get('socialuni/notify/queryUnreadNotifiesAndUpdateHasRead')
  }
}
