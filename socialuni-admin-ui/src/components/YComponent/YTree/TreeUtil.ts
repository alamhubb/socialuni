export default class TreeUtil {
  // 压平数组
  static flatTree(trees: any[]) {
    const flatTrees = []
    if (trees && trees.length) {
      trees.forEach(node => {
        flatTrees.push(node)
        flatTrees.push(...this.flatTree(node.children))
      })
    }
    return flatTrees
  }

  // list转tree
  static listToTree(list: any[]): any[] {
    const treeMap = new Map<number, any>()
    for (const listElement of list) {
      listElement.children = []
      treeMap.set(listElement.id, listElement)
    }
    for (const listElement of list) {
      if (listElement.pid) {
        const parent = treeMap.get(listElement.pid)
        parent.children.push(listElement)
      }
    }
    // 只返回没有pid的
    return list.filter(item => !item.pid)
  }

  static recurseSetActiveFalse(array, vue) {
    for (const row of array) {
      vue.$set(row, 'active', false)
      if (row.children && row.children.length) {
        this.recurseSetActiveFalse(row.children, vue)
      }
    }
  }
}
