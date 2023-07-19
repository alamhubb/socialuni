import NodeType from '@/constant/common/NodeType'

export default class MappingDesignViewTabName {
  // 数据源节点
  static readonly mapping = 'mapping'
  static readonly sourceData = 'sourceData'
  // 转换器节点
  static readonly transform = 'transform'
  // 目标节点
  static readonly targetData = 'targetData'

  static getNodeType(tabName) {
    if (tabName === this.sourceData) {
      return NodeType.sourceData
    } else if (tabName === this.transform) {
      return NodeType.transform
    } else if (tabName === this.targetData) {
      return NodeType.targetData
    }
    return null
  }
}
