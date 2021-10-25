import UserImg from '@/model/UserImg'
import ComConst from '@/constants/ComConst'

export default class UserIdCard {
  userId: number
  nickname: string
  gender: string
  imgs: UserImg []
  idCard: string
  previewImgs: string []
  createDate: Date
  checked: boolean

  constructor(user?: UserIdCard) {
    if (user) {
      this.userId = user.userId
      this.nickname = user.nickname
      this.gender = user.gender
      this.imgs = UserImg.getUserImgsByVOs(user.imgs)
      this.idCard = user.idCard
      this.createDate = new Date(user.createDate)
      this.checked = false
      this.previewImgs = this.imgs.map(item => ComConst.imgUrl + item.img)
      this.previewImgs.unshift(ComConst.imgUrl + this.idCard)
    }
  }

  //后台返回的只有属性，不能使用类的方法，但并不是class类，需要转为UserClass类
  public static getUsersByVOs(users: UserIdCard[]): UserIdCard[] {
    return users.map(user => new UserIdCard(user))
  }
}
