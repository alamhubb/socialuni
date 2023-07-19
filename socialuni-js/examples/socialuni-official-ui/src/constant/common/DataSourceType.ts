import EnumNumVO from '@/model/base/EnumNumVO'

export default class DataSourceType {
  //数据库
  static readonly DataBaseDataSource: number = 1
  //文件处理
  static readonly FileSystemDataSource: number = 5
  //应用程序
  static readonly ApplicationDataSource: number = 14
  //文件传输
  static readonly FileTransferDataSource: number = 23
  //实时接入
  static readonly RealAccessDataSource: number = 35

  static readonly DataBaseDataSourceEnum: EnumNumVO = new EnumNumVO(DataSourceType.DataBaseDataSource, 'DataBaseDataSource')
  static readonly FileSystemDataSourceEnum: EnumNumVO = new EnumNumVO(DataSourceType.FileSystemDataSource, 'FileSystemDataSource')
  static readonly ApplicationDataSourceEnum: EnumNumVO = new EnumNumVO(DataSourceType.ApplicationDataSource, 'ApplicationDataSource')
  static readonly FileTransferDataSourceEnum: EnumNumVO = new EnumNumVO(DataSourceType.FileTransferDataSource, 'FileTransferDataSource')
  static readonly RealAccessDataSourceEnum: EnumNumVO = new EnumNumVO(DataSourceType.RealAccessDataSource, 'RealAccessDataSource')

  static readonly enum_map: Map<number, string> = new Map<number, string>()

  static getTypeName(value: number): string {
    return DataSourceType.enum_map.get(value)
  }
}

for (const key in DataSourceType) {
  if (DataSourceType[key] instanceof EnumNumVO) {
    DataSourceType.enum_map.set(DataSourceType[key].value, DataSourceType[key].name)
  }
}

