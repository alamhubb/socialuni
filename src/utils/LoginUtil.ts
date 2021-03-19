export default class LoginUtil {
  static checkSession (): Promise<any> {
    return new Promise<any>((resolve, reject) =>
      uni.checkSession({
        success () {
          resolve(null)
        },
        fail (err) {
          reject(err)
        }
      })
    )
  }
}
