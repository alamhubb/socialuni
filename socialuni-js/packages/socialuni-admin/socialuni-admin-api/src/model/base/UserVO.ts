export default class UserVO {
  userName: string
  password: string
  delState: number
  fullName: string
  id: number
  karmaUserId: number
  loginPermission: number
  realName: string
  userEmail: string
  userKey: string

  constructor(userName: string, password: string) {
    this.userName = userName
    this.password = password
  }
}
