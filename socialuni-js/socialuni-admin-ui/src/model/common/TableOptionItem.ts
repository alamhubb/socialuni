export default class TableOptionItem {
  value: string = null
  label: string = null
  type: string = null
  multiple = false
  selectArr: TableOptionItem[] = null

  constructor(value: string, label: string, selectArr: any[] = null, type = null, multiple = false) {
    this.value = value
    this.label = label
    if (selectArr) {
      this.selectArr = selectArr.map(item => new TableOptionItem(item.value, item.name))
    }
    this.type = type || null
    this.multiple = multiple || false
  }
}
