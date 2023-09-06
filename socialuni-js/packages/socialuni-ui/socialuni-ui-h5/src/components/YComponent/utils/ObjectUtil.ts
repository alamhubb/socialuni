export default class ObjectUtil {
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

  static toFormData(object: Record<string, any> | any): FormData {
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

  static toParamData(object: Record<string, any>): string {
    const paramObj = new URLSearchParams()
    Object.keys(object).forEach((key) => {
      const data = object[key]
      if (data instanceof Array) {
        data.forEach((item) => {
          paramObj.append(key, item)
        })
      } else {
        if (data !== null) {
          paramObj.append(key, data)
        }
      }
    })
    return paramObj.toString()
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

  private static isUndefined(obj) {
    return obj === undefined
  }

  static isEmpty(value: string) {
    return value === null || value === '' || value === undefined
  }

  // 如果undefined返回null
  static getValue<T>(obj: T): T {
    return this.isUndefined(obj) ? null : obj
  }

  static getValueByOrZero<T>(obj: any, obj1: T = null): T {
    const value = this.getValue(obj)
    if (value === 0) {
      return value
    } else if (value === false) {
      return value
    }
    return obj || obj1
  }

  static getValueByOrZeroOrFalseElseNull<T>(obj: any, obj1: T = null): T {
    const value = this.getValue(obj)
    if (value === 0) {
      return value
    } else if (value === false) {
      return value
    }
    return obj || obj1
  }

  static spread(...objs): any {
    const obj = {}
    for (const obj1 of objs) {
      // 基本类型没办法合并
      if (obj1 instanceof Object) {
        Object.assign(obj, obj1)
      }
    }
    return obj
  }
}
