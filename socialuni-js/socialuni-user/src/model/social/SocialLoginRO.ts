export default class SocialLoginRO<T> {
  token: string = null
  user: T = null


  constructor (token: string, user: T) {
    this.token = token
    this.user = user
  }
}
