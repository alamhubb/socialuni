export default class JsonUtil {
  // 改成any类型
  static deepClone<T>(object: T): T {
    return this.toParse(this.toJson(object))
  }

  static toJson(object: any): string {
    if (object || (object === 0) || (object === false)) {
      return JSON.stringify(object)
    }
    return ''
  }

  static toParse(object: string): any {
    if (object) {
      return JSON.parse(object)
    }
    return ''
  }

  static log(object: any) {
    console.trace(this.toJson(object))
  }

  static logObj(object: any) {
    console.trace(this.deepClone(object))
  }

  static toFormData(object: Record<string, any>): FormData {
    const formData = new FormData()
    Object.keys(object).forEach((key) => {
      const data = object[key]
      if (data instanceof Array) {
        data.forEach((item) => {
          formData.append(key, item)
        })
      } else {
        if (data !== null) {
          formData.append(key, data)
        }
      }
    })
    return formData
  }

  // 需要将叶子节点的 children 设置为 null
  /* static recursionSetChildrenNull(tree: TreeVO) {
    if (tree.leaf) {
      tree.children = null
    } else {
      for (const child of tree.children) {
        this.recursionSetChildrenNull(child)
      }
    }
  }*/

  // 递归清空子节点
  static treeRecursion(data: any[]) {
    for (const item of data) {
      if (item.childList && item.childList.length > 0) {
        this.treeRecursion(item.childList)
      } else {
        item.childList = null
      }
    }
    return data
  }
}
