import {appModule} from "@/plugins/store";

export default class SocialUniAuthVO {
  authType: string
  appSecretKey: string
  appUserId: string
  // appId: string


  //用户id选填，不填将联盟生成并返回
  constructor(authType: string, appUserId?: string) {
    this.authType = authType;
    this.appSecretKey = appModule.appSocialSecretKey;
    this.appUserId = appUserId;
  }
}
