export default class ResultVO<T> {
  errorCode: number
  errorMsg: string
  success: boolean
  data: T

  constructor () {
    this.errorCode = 0
    this.errorMsg = ''
    this.success = true
  }
}
