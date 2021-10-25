import UserImg from "@/model/UserImg"
import District from "@/model/District"

export default class User {
  id: number = null
  nickname: string = null
  gender: string = null
  avatar: string = null
  status: string = null
  age: number = null
  location: string = null
  birthday: string = null
  imgs: UserImg [] = null
  //用来区分是否已经喜欢过
  likeDisabled: boolean = null
  idCardStatus: string = null
  faceRatio: number = null
  likeCount: number = null
  onlineFlag: boolean = null
  lastOnlineDate: Date = null
  //是否为vip，
  vipFlag: boolean = null
  //是否为年会员
  yearVipFlag: boolean = null
  //邀请码，你分享给别人的邀请码
  inviteCode: string = null
  //邀请人，谁邀请的你
  registerInviteUser: User = null
  //vip到期时间
  vipEndDate: Date = null
  //用户拥有的清池币数量
  qcb: number = null
  fansNum: number = null
  followNum: number = null
  talkQueryDistrict: District = null
  hasFollowed: boolean = true
  useNearby: number = null
  wxAccount: string = null
  qqAccount: string = null
  wbAccount: string = null
  userType: string = null
  followStatus: string = null
  //对方是否关注了自己
  beFollow: boolean = null
  loveValue: number = null
}
