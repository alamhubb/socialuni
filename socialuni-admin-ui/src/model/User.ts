import UserImg from '@/model/UserImg'

export default class User {
  id: number
  nickname: string
  gender: string
  age: number
  imgs: UserImg []

  constructor(user: User) {
    this.id = user.id
    this.nickname = user.nickname
    this.gender = user.gender
    this.age = user.age
    this.imgs = user.imgs
  }

  //后台返回的只有属性，但并不是class类，需要转为UserClass类
  public static getUsersByVOs(users: User[]): User[] {
    return users.map(user => new User(user))
  }
}
