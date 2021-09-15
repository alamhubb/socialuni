import ImgFileVO from '../ImgFileVO'

export default class CenterUserDetailRO {
  id: string = null
  nickname: string = ''
  avatar: string = ''
  gender: string = null
  age: number = null
  city = ''
  birthday: string = null
  phoneNum: string = null
  bindSocialuni: boolean = null
  socialuniNickname: string = null
  hasFollowed: boolean = null
  hasBeFollowed: boolean = null
  isMine: boolean = null
  fansNum: number = null
  followNum: number = null
  imgs: ImgFileVO [] = null
}
