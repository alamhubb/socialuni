import socialuniAppRequest from "../socialuniAppRequest";

export default class NotifyAPI {
  static queryUnreadNotifiesAPI() {
    return socialuniAppRequest.get('haxun/cp/queryUnreadNotifies')
  }

  static queryNotifiesAndUpdateHasReadAPI() {
    return socialuniAppRequest.get('haxun/cp/queryNotifies')
  }
}
