export default class TreeVO {
  value: string = null
  label: string = null
  level: number = null
  type: string = null
  leaf: boolean = null
  dbName: string = null
  sqlDbName: string = null
  children: TreeVO [] = []

  constructor(value: string, label: string, children?: TreeVO[]) {
    this.value = value
    this.label = label
    this.children = children
  }
}
