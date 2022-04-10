export default class TreeVO<T> {
  pid: any = null
  id: any = null
  isLeaf?: boolean = null
  label: string = null
  type?: any = null
  data?: T = null
  children?: TreeVO<T> [] = []
  checked?: boolean = false
}
