export default class EnumStrVO<T = string> {
  value: T = null
  label: string | T = null

  constructor (value: T, name?: string | T) {
    this.value = value
    this.label = name || value
  }
}
