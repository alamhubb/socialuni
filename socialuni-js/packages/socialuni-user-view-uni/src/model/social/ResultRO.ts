export default class ResultRO<T> {
  errorCode: number
  errorMsg: string
  success: boolean
  data: T
}
