export default class TableSelectOptionItem {
  value: any = null
  label: any = null

  constructor(value: any, label: any = null) {
    this.value = value
    if (label === null) {
      this.label = value
    } else {
      this.label = label
    }
  }
}
