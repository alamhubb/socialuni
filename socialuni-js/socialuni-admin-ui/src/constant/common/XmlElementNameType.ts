export default class XmlElementNameType {
  static nodeId = 'nodeId'
  static mxGraphModel = 'mxGraphModel'
  static root = 'root'
  static mxCell = 'mxCell'
  static yoyoNode = 'yoyoNode'
  static Sending = 'Sending'
  static ReplNode = 'ReplNode'
  static configExt = 'configExt'
  static merge = 'merge'
  static mergeParam = 'mergeParam'
  static baseInfo = 'baseInfo'
  static crossDomain = 'crossDomain'
  static machineIp = 'machineIp'
  static tableName = 'tableName'
  // 实时数据库的属性
  static tableNames = 'tableNames'
  static sqlType = 'sqlType'
  static startPosition = 'startPosition'
  static distribute = 'distribute'
  static nodeType = 'nodeType'
  static transType = 'transType'

  static oldXmlProps = [XmlElementNameType.crossDomain, XmlElementNameType.machineIp, XmlElementNameType.tableName,
    XmlElementNameType.distribute, XmlElementNameType.nodeType, XmlElementNameType.transType]

  static globalParams = 'globalParams'
  static mxCols = 'mxCols'
  static col = 'col'
  static mxGeometry = 'mxGeometry'
  static mxRectangle = 'mxRectangle'
  static Relation = 'Relation'

  static position = 'position'
  static size = 'size'

  // 连线相关
  static source = 'source'
  static target = 'target'

  // 转换器相关
  static params = 'params'
  // 自定义转换器使用
  static field = 'field'
  static fieldName = 'fieldName'
  static value = 'value'

  static comment = 'comment'
  static fieldDecimals = 'fieldDecimals'
  static isIdentity = 'isIdentity'
  static isIdentityPk = 'isIdentityPk'
  static isNull = 'isNull'
  static isPk = 'isPk'

  static keyStream = 'keyStream'
  static keyLookup = 'keyLookup'
  static keyCondition = 'keyCondition'

  static updateLookup = 'updateLookup'
  static updateStream = 'updateStream'
  static update = 'update'

  static inStreamField = 'inStreamField'
  static outStreamField = 'outStreamField'
  static trimType = 'trimType'
  static lowerUpper = 'lowerUpper'
  static padding = 'padding'
  static padChar = 'padChar'
  static padLength = 'padLength'
  static initCap = 'initCap'
  static digits = 'digits'
  static escape = 'escape'
  static removeSpecialCharacter = 'removeSpecialCharacter'
}
