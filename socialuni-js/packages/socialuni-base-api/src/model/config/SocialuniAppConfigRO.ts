export default class SocialuniAppConfigRO {
  //首页的tab栏目
  followTabName: string = null
  homeTabName: string = null
  cityTabName: string = null
  selfSchoolTabName: string = null
  systemUserId: string = null
  tabNames: string[] = null


  //是否禁止未成年人内容
  disableUnderageContent: boolean = null;

  //是否禁止内容中包含联系方式
  disableContentHasContactInfo: boolean = null;

  //是否禁止内容中包含二维码
  disableContentHasQrCode: boolean = null;

  //是否必须填写校园才允许发帖
  mustSetSchoolCanPost: boolean = null;

  //todo 相关功能未实现 用户必须实名认证
  userMustIdentityAuth: boolean = null;

  //配置3，应用性别
  appGender: string = null
}
