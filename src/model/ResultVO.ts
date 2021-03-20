export default class ResultVO<T> {
  errorCode: number
  errorMsg: string
  success: boolean
  data: T
}
