export default class EnumStrVO<T = string> {
  value: T = null
  name: string | T = null

  constructor(value: T, name?: string | T) {
    this.value = value
    this.name = name || value
  }

  static nullEnum = new EnumStrVO(null)
}
