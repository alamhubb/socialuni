import TableSelectOptionItem from '@/components/YComponent/TableGroup/TableSelectOptionItem'

export default class TableOptionItem {
  value: string = null
  label: string = null
  type: string = null
  multiple = false
  selectArr: TableSelectOptionItem[] = null

  constructor(value: string, label: string, selectArr: any[] = null, type = null, multiple = false) {
    this.value = value
    this.label = label
    if (selectArr) {
      if (selectArr.length) {
        if (selectArr[0].label || selectArr[0].label === 0) {
          this.selectArr = selectArr
        } else {
          this.selectArr = selectArr.map(item => new TableSelectOptionItem(item.value, item.name))
        }
      } else {
        this.selectArr = []
      }
    } else {
      // 默认值设置为空数组，会导致本该是输入框的筛选条件变为下拉框
      this.selectArr = null
    }
    this.type = type || null
    this.multiple = multiple || false
  }
}
