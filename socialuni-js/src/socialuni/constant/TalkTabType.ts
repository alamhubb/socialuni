import SocialuniAppType from "@/socialuni/constant/SocialuniAppType";

export default class TalkTabType {
  // 系统
  static readonly follow_name: string = '关注'
  static readonly follow_type: string = 'follow'

  static readonly home_name: string = '首页'
  static readonly home_type: string = 'home'

  static readonly school_name: string = '校园'
  static readonly school_type: string = SocialuniAppType.school


  static readonly city_name: string = '同城'
  static readonly city_type: string = 'city'

  //圈子类型
  static readonly circle_type: string = 'circle'
}
