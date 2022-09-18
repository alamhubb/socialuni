import GenderType from "@/socialuni/constant/GenderType";

export default class SocialuniAppConfigRO {//首页的tab栏目
  //是否禁止未成年人内容
  disableUnderageContent = false;

  //是否禁止内容中包含联系方式
  disableContentHasContactInfo = false;

  //是否禁止内容中包含二维码
  disableContentHasQrCode = false;

  //是否必须填写校园才允许发帖
  mustSetSchoolCanPost = false;

  //todo 相关功能未实现 用户必须实名认证
  userMustIdentityAuth = false;

  //配置3，应用性别
  appGender = GenderType.all;
}
