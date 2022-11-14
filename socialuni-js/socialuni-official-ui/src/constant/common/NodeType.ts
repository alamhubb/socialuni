import EnumNumVO from '@/model/base/EnumNumVO'

export default class NodeType {
  // 数据源节点
  static readonly sourceData: number = 1
  // 转换器节点
  static readonly transform: number = 2
  // 目标节点
  static readonly targetData: number = 3

  static isDataSource(nodeType) {
    return this.isSourceData(nodeType) || this.isTargetData(nodeType)
  }

  static isSourceData(nodeType) {
    return nodeType === this.sourceData
  }

  static isTargetData(nodeType) {
    return nodeType === this.targetData
  }

  static isTransform(nodeType) {
    return nodeType === this.transform
  }

  private static readonly sourceNodeEnum: EnumNumVO = new EnumNumVO(NodeType.sourceData, '数据源')
  private static readonly targetNodeEnum: EnumNumVO = new EnumNumVO(NodeType.targetData, '目标节点')

  static readonly enum_map: Map<number, string> = new Map<number, string>()

  static getTypeName(value: number) {
    return NodeType.enum_map.get(value)
  }
}

for (const key in NodeType) {
  if (NodeType[key] instanceof EnumNumVO) {
    NodeType.enum_map.set(NodeType[key].value, NodeType[key].name)
  }
}
