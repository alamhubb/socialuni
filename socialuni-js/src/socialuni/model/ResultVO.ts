export default class ResultVO<T> {
  errorCode: number
  errorMsg: string
  success: boolean
  data: T

  constructor (success = true) {
    this.errorCode = 0
    this.errorMsg = ''
    this.success = success
  }
}
