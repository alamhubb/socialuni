export default class Response<T> {
  errorCode: number
  errorMsg: string
  data: T
}
