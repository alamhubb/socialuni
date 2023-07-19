export default class TableSelectOptionItem {
    value: string = null
    label: any = null

    constructor(value: string, label: any = null) {
      this.value = value
      if (label === null) {
        this.label = value
      } else {
        this.label = label
      }
    }
}
