import TableOptionItem from '@/model/common/TableOptionItem'
import TableSelectTagVO from '@/model/common/TableSelectTagVO'

export default class TableSelect {
  defaultValue: string = null
  optionList: TableOptionItem[] = []
  searchTags: TableSelectTagVO[] = []

  constructor(optionList: TableOptionItem[], defaultValue: string = null, selectTags = []) {
    this.optionList = optionList
    if (defaultValue) {
      this.defaultValue = defaultValue
    } else {
      this.defaultValue = optionList[0].value
    }
    this.searchTags = selectTags
  }
}
