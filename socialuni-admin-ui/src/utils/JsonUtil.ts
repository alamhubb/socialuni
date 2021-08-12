import TreeVO from '@/model/base/TreeVO'

export default class JsonUtil {
  // 改成any类型
  static deepClone(object: any): any {
    return JSON.parse(JSON.stringify(object))
  }

  static toJson(object: any): string {
    return JSON.stringify(object)
  }

  static jsonParse(objJson: string): any {
    return JSON.parse(objJson)
  }

  static log(object: any) {
    console.log(JSON.stringify(object))
  }

  static toFormData(object: Record<string, any>): FormData {
    const formData = new FormData()
    Object.keys(object).forEach((key) => {
      formData.append(key, object[key])
    })
    return formData
  }

  // 需要将叶子节点的 children 设置为 null
  static recursionSetChildrenNull(tree: TreeVO) {
    if (tree.leaf) {
      tree.children = null
    } else {
      for (const child of tree.children) {
        this.recursionSetChildrenNull(child)
      }
    }
  }

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
