import ImgFileVO from '../ImgFileVO'

export default class CenterUserDetailRO {
  id: string = null
  nickname: string = ''
  avatar: string = ''
  gender: string = null
  schoolName: string = null
  age: number = null
  identityAuth: boolean = false
  city = ''
  birthday: string = null
  phoneNum: string = null
  bindedSocialuni: boolean = null
  socialuniNickname: string = null
  hasFollowed: boolean = null
  hasBeFollowed: boolean = null
  isMine: boolean = null
  fansNum: number = null
  followNum: number = null
  imgs: ImgFileVO [] = null
}
