export default class EnumStrVO<T = string> {
  value: T = null
  //因为uview组件默认为lable，所以改用label
  label: string | T = null

  constructor (value: T, name?: string | T) {
    this.value = value
    this.label = name || value
  }
}
