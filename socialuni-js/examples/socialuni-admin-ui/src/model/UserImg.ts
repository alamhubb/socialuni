export default class UserImg {
  id: number
  img: string
  src: string
  aspectRatio: number
  checked: boolean

  constructor(userImg: UserImg) {
    this.id = userImg.id
    this.img = userImg.img
    this.aspectRatio = userImg.aspectRatio
    this.checked = false
  }

  // 后台返回的只有属性，不能使用类的方法，但并不是class类，需要转为UserClass类
  public static getUserImgsByVOs(userImgs: UserImg[]): UserImg[] {
    return userImgs.map(userImg => new UserImg(userImg))
  }
}
