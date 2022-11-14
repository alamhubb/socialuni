export default class DataTableColumnTypeEnum {
  static index = 'index'
  static selection = 'selection'
  static label = 'label'
  static button = 'button'
  static input = 'input'
  static select = 'select'
  static delete = 'delete'
  static checked = 'checked'

  static values: string[] = []
  static elTableTypes: string[] = []
}

DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.input)
DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.select)
DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.label)
DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.button)
DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.delete)
DataTableColumnTypeEnum.values.push(DataTableColumnTypeEnum.checked)

DataTableColumnTypeEnum.elTableTypes.push(DataTableColumnTypeEnum.index)
DataTableColumnTypeEnum.elTableTypes.push(DataTableColumnTypeEnum.selection)

