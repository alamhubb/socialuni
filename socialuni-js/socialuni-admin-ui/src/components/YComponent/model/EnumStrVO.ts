export default class EnumStrVO<T = any> {
  value: any = null
  name: string = null
  data: T = null

  constructor(value: any, name?: string, data: T = null) {
    this.value = value
    this.name = name || value
    this.data = data
  }

  static nullEnum = new EnumStrVO(null)
}
