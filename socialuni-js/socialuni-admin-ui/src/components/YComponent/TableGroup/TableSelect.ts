import TableOptionItem from '@/components/YComponent/TableGroup/TableOptionItem'
import TableSelectTagVO from '@/components/YComponent/TableGroup/TableSelectTagVO'

export default class TableSelect {
  defaultValue: string = null
  optionList: TableOptionItem[] = []
  searchTags: TableSelectTagVO[] = []

  constructor(optionList: TableOptionItem[], defaultValue: string = null, selectTags = []) {
    this.optionList = optionList
    if (defaultValue) {
      this.defaultValue = defaultValue
    } else {
      if (this.optionList.length) {
        this.defaultValue = optionList[0].value
      }
    }
    this.searchTags = selectTags
  }
}
