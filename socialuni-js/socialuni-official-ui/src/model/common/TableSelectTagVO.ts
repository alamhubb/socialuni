export default class TableSelectTagVO {
  label: string
  labelName: string
  valueName: string
  value: string

  constructor(label: string, labelName: string, valueName: any, value: any) {
    this.label = label
    this.labelName = labelName
    this.valueName = valueName
    this.value = value
  }
}
