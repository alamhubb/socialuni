import EnumStrVO from '@/model/base/EnumStrVO'

// 大小写NULL
export default class TransformTransType {
  // 转换器类型
  // 字符串操作
  static readonly StringOperations: string = 'StringOperations'
  // 自定义
  static readonly CustomizeTran: string = 'CustomizeTran'
  // 标准校验
  static readonly Validator: string = 'Validator'
  // 条件分流
  static readonly ConditionStream: string = 'ConditionStream'
  // 字符拆分
  static readonly FieldSplitter: string = 'FieldSplitter'

  // 替换字符
  static readonly ReplaceString: string = 'ReplaceString'
  // 常量
  static readonly SetValueConstant: string = 'SetValueConstant'
  // 字段值
  static readonly SetValueField: string = 'SetValueField'
  // 剪切
  static readonly StringCut: string = 'StringCut'
  // 去重
  static readonly Unique: string = 'Unique'
  // 唯一行
  static readonly UniqueRowsByHashSet: string = 'UniqueRowsByHashSet'
  // 排序记录
  static readonly SortRows: string = 'SortRows'
  // 后台就是这样，所以不要删除3，以后可以商量删除
  // 列拆分
  static readonly SplitFieldToRows3: string = 'SplitFieldToRows3'
  // 生成记录
  static readonly RowGenerator: string = 'RowGenerator'
  // rest操作
  static readonly Rest: string = 'Rest'
  // JSON解析
  static readonly JsonInput: string = 'JsonInput'
  // 替换null
  static readonly IfNull: string = 'IfNull'

  // 组排序
  static readonly GroupBy: string = 'GroupBy'
  // 值映射
  static readonly ValueMapper: string = 'ValueMapper'
  // 计算器
  static readonly Calculator: string = 'Calculator'
  // 改变序列
  static readonly FieldsChangeSequence: string = 'FieldsChangeSequence'
  // 对称加密
  static readonly SymmetricCrypto: string = 'SymmetricCrypto'
  // 空操作
  static readonly ToNull: string = 'ToNull'
  // 扁平化
  static readonly Flattener: string = 'Flattener'
  // java过滤器
  static readonly JavaFilter: string = 'JavaFilter'
  // 合并连接
  static readonly MergeJoin: string = 'MergeJoin'
  // 行转列
  static readonly Normaliser: string = 'Normaliser'
  // SwitchCase
  static readonly SwitchCase: string = 'SwitchCase'
  // webservice查询
  static readonly WebServiceLookup: string = 'WebServiceLookup'

  static readonly StringOperationsEnum: EnumStrVO = new EnumStrVO(TransformTransType.StringOperations, '字符串操作')
  static readonly FieldSplitterEnum: EnumStrVO = new EnumStrVO(TransformTransType.FieldSplitter, '字符拆分')
  static readonly GroupByEnum: EnumStrVO = new EnumStrVO(TransformTransType.GroupBy, '组排序')
  static readonly ValueMapperEnum: EnumStrVO = new EnumStrVO(TransformTransType.ValueMapper, '值映射')
  static readonly CalculatorEnum: EnumStrVO = new EnumStrVO(TransformTransType.Calculator, '计算器')
  static readonly FlattenerEnum: EnumStrVO = new EnumStrVO(TransformTransType.Flattener, '扁平化')
  static readonly FieldsChangeSequenceEnum: EnumStrVO = new EnumStrVO(TransformTransType.FieldsChangeSequence, '改变序列')
  static readonly NormaliserEnum: EnumStrVO = new EnumStrVO(TransformTransType.Normaliser, '行转列')
  static readonly MergeJoinEnum: EnumStrVO = new EnumStrVO(TransformTransType.MergeJoin, '合并连接')
  static readonly JavaFilterEnum: EnumStrVO = new EnumStrVO(TransformTransType.JavaFilter, 'Java代码过滤')
  static readonly ToNullEnum: EnumStrVO = new EnumStrVO(TransformTransType.ToNull, '空操作')
  static readonly WebServiceLookupEnum: EnumStrVO = new EnumStrVO(TransformTransType.WebServiceLookup, 'webservice查询')
  static readonly SymmetricCryptoEnum: EnumStrVO = new EnumStrVO(TransformTransType.SymmetricCrypto, '对称加密')
  static readonly SwitchCaseEnum: EnumStrVO = new EnumStrVO(TransformTransType.SwitchCase, 'SwitchCase')

  static readonly ReplaceStringEnum: EnumStrVO = new EnumStrVO(TransformTransType.ReplaceString, '替换字符')
  static readonly SetValueConstantEnum: EnumStrVO = new EnumStrVO(TransformTransType.SetValueConstant, '常量')
  static readonly SetValueFieldEnum: EnumStrVO = new EnumStrVO(TransformTransType.SetValueField, '字段值')
  static readonly StringCutEnum: EnumStrVO = new EnumStrVO(TransformTransType.StringCut, '剪切')
  static readonly UniqueEnum: EnumStrVO = new EnumStrVO(TransformTransType.Unique, '去重')
  static readonly UniqueRowsByHashSetEnum: EnumStrVO = new EnumStrVO(TransformTransType.UniqueRowsByHashSet, '唯一行')
  static readonly SortRowsEnum: EnumStrVO = new EnumStrVO(TransformTransType.SortRows, '排序记录')
  static readonly SplitFieldToRows3Enum: EnumStrVO = new EnumStrVO(TransformTransType.SplitFieldToRows3, '列拆分')
  static readonly RowGeneratorEnum: EnumStrVO = new EnumStrVO(TransformTransType.RowGenerator, '生成记录')
  static readonly RestEnum: EnumStrVO = new EnumStrVO(TransformTransType.Rest, 'rest操作')
  static readonly JsonInputEnum: EnumStrVO = new EnumStrVO(TransformTransType.JsonInput, 'JSON解析')
  static readonly IfNullEnum: EnumStrVO = new EnumStrVO(TransformTransType.IfNull, '替换null')

  static readonly ValidatorEnum: EnumStrVO = new EnumStrVO(TransformTransType.Validator, '标准验证')
  static readonly ConditionStreamEnum: EnumStrVO = new EnumStrVO(TransformTransType.ConditionStream, '条件分流')

  static readonly enums: EnumStrVO[] = []

  static readonly enum_map: Map<string, string> = new Map<string, string>()

  static getTypeName(value: string) {
    return TransformTransType.enum_map.get(value)
  }
}

for (const key in TransformTransType) {
  if (TransformTransType[key] instanceof EnumStrVO) {
    TransformTransType.enums.push(TransformTransType[key])
    TransformTransType.enum_map.set(TransformTransType[key].value, TransformTransType[key].name)
  }
}

