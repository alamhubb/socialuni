export default class TreeVO<T> {
  pid: any = null
  id: any = null
  label: string = null
  data?: T = null
  children?: TreeVO<T> [] = []
  checked?: boolean = false
}
