/**
 * @Author qinkaiyuan
 * @Date 2021-03-13 18:09
 * @Version 1.0
 */
export default class Arrays {
  static has(array, value, prop?: string) {
    return !!this.find(array, value, prop)
  }

  static find(array, value, prop?: string) {
    return array[this.findIndex(array, value, prop)]
  }

  static findIndex(array, value, prop?: string) {
    const index = array.findIndex(item => {
      console.log(prop)
      console.log(item)
      console.log(item[prop])
      return prop ? (item[prop] === value) : (item === value)
    })
    return index
  }

  // 插叙添加时可选择的数据源
  static delete(array, value, prop?: string) {
    const delIndex = this.findIndex(array, value, prop)
    console.log(array)
    console.log(delIndex)
    if (delIndex > -1) {
      const del = array.splice(delIndex, 1)
      console.log(del)
    }
  }

  static deleteAry(array, delAry, prop?: string) {
    for (const delAryElement of delAry) {
      this.delete(array, delAryElement[prop] || delAryElement, prop)
    }
  }

  static unique(array, prop?: string) {
    const res = new Map()
    return array.filter((item) => {
      return !res.has(item[prop] || item) && res.set(item[prop] || item, 1)
    })
  }
}
