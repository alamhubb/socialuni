import EnumNumVO from '@/model/base/EnumNumVO'

// 大小写NULL
export default class DataSourceIdEnum {
  static readonly mysql: number = 2
  static readonly db2: number = 3
  static readonly hbase: number = 4
  static readonly kafka: number = 15
  static readonly oracle: number = 16
  static readonly kingbase: number = 17
  static readonly sqlServer: number = 19

  static readonly postgreSql: number = 20
  static readonly file: number = 21
  static readonly gbase: number = 22
  static readonly mongoDB: number = 24
  static readonly bitsFlow: number = 26

  static readonly emr: number = 27
  static readonly dameng: number = 29
  static readonly kunlun: number = 33
  static readonly shenTong: number = 34
  static readonly mysqlIm: number = 36
  static readonly odps: number = 38

  static readonly oracleExpDbFile: number = 42
  static readonly oracleImpDbFile: number = 44
  static readonly oracleExpTbFile: number = 45
  static readonly oracleImpTbFile: number = 46
  static readonly odpsTunnel: number = 47
  static readonly informix: number = 48
  static readonly greenPlum: number = 49

  static readonly oracleExpDbDump: number = 50
  static readonly oracleImpDbDump: number = 51
  static readonly oracleExpTbDump: number = 52
  static readonly oracleImpTbDump: number = 53
  static readonly oracleSsTb: number = 55
  static readonly oracleSsDb: number = 56
  static readonly mysqlSsTb: number = 57
  static readonly mysqlSsDb: number = 58
  static readonly impala: number = 59

  static readonly librA: number = 60
  static readonly excel: number = 63
  static readonly elasticSearch: number = 64
  static readonly gbase8t: number = 65
  static readonly rocketMq: number = 66
  static readonly oss: number = 67
  static readonly hdfsFile: number = 68
  static readonly ftpSftpFile: number = 69

  static readonly hwHive: number = 71
  static readonly saisiHive: number = 72
  static readonly sftp: number = 75
  static readonly ftp: number = 76
  static readonly hdfs: number = 77
  static readonly local: number = 78
  static readonly yyHive: number = 79

  static readonly yyHive2: number = 80
  static readonly gushu: number = 81
  static readonly kingbaseV8: number = 82
  static readonly kingbaseAnalytics: number = 83
  static readonly dataFuse: number = 84
  static readonly commonJdbc: number = 85
  static readonly oracleIm: number = 86
  static readonly postgreSqlIm: number = 87
  static readonly FILE_MANAGER: number = 88
  static readonly Excel_file_manager: number = 89

  static readonly mysqlEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.mysql, 'mysql')
  static readonly db2Enum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.db2, 'db2')
  static readonly hbaseEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.hbase, 'hbase')
  static readonly kafakaEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.kafka, 'kafka')
  static readonly oracleEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracle, 'oracle')
  static readonly kingbaseEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.kingbase, 'kingbase')
  static readonly sqlServerEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.sqlServer, 'sqlServer')

  static readonly postgreSqlEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.postgreSql, 'postgreSql')
  static readonly fileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.file, 'file')
  static readonly gbaseEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.gbase, 'gbase')
  static readonly mongoDBEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.mongoDB, 'mongoDB')
  static readonly bitsFlowEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.bitsFlow, 'bitsFlow')
  static readonly emrEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.emr, 'emr')
  static readonly damengEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.dameng, 'dameng')
  static readonly kunlunEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.kunlun, 'kunlun')
  static readonly shenTongEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.shenTong, 'shenTong')
  static readonly mysqlImEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.mysqlIm, 'mysqlIm')
  static readonly oracleImEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleIm, 'oracleIm')
  static readonly postgreSqlImEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.postgreSqlIm, 'postgreSqlIm')
  static readonly odpsEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.odps, 'odps')

  static readonly oracleExpDbFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleExpDbFile, 'oracleExpDbFile')
  static readonly oracleImpDbFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleImpDbFile, 'oracleImpDbFile')
  static readonly oracleExpTbFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleExpTbFile, 'oracleExpTbFile')
  static readonly oracleImpTbFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleImpTbFile, 'oracleImpTbFile')
  static readonly odpsTunnelEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.odpsTunnel, 'odpsTunnel')
  static readonly informixEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.informix, 'informix')
  static readonly greenPlumEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.greenPlum, 'greenPlum')
  static readonly oracleExpDbDumpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleExpDbDump, 'oracleExpDbDump')
  static readonly oracleImpDbDumpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleImpDbDump, 'oracleImpDbDump')
  static readonly oracleExpTbDumpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleExpTbDump, 'oracleExpTbDump')
  static readonly oracleImpTbDumpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleImpTbDump, 'oracleImpTbDump')
  static readonly oracleSsTbEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleSsTb, 'oracleSsTb')
  static readonly oracleSsDbEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oracleSsDb, 'oracleSsDb')
  static readonly mysqlSsTbEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.mysqlSsTb, 'mysqlSsTb')
  static readonly mysqlSsDbEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.mysqlSsDb, 'mysqlSsDb')
  static readonly impalaEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.impala, 'impala')

  static readonly librAEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.librA, 'librA')
  static readonly excelEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.excel, 'excel')
  static readonly elasticSearchEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.elasticSearch, 'elasticSearch')
  static readonly gbase8tEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.gbase8t, 'gbase8t')
  static readonly rocketMqEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.rocketMq, 'rocketMq')
  static readonly ossEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.oss, 'oss')
  static readonly ftpSftpFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.ftpSftpFile, 'ftpSftpFile')
  static readonly hdfsFileEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.hdfsFile, 'hdfsFile')
  static readonly hdfsEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.hdfs, 'hdfs')
  static readonly sftpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.sftp, 'sftp')
  static readonly hwHiveEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.hwHive, 'hwHive')
  static readonly saisiHiveEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.saisiHive, 'saisiHive')
  static readonly ftpEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.ftp, 'ftp')
  static readonly localEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.local, 'local')
  static readonly yyHiveEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.yyHive, 'yyHive')

  static readonly yyHive2Enum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.yyHive2, 'yyHive2')
  static readonly gushuEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.gushu, 'gushu')
  static readonly kingbaseV8Enum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.kingbaseV8, 'kingbaseV8')
  static readonly kingbaseAnalyticsEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.kingbaseAnalytics, 'kingbaseAnalytics')
  static readonly dataFuseEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.dataFuse, 'dataFuse')
  static readonly commonJdbcEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.commonJdbc, 'commonJdbc')
  static readonly fILE_MANAGEREnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.FILE_MANAGER, 'FILE_MANAGER')
  static readonly excel_file_managerEnum: EnumNumVO = new EnumNumVO(DataSourceIdEnum.Excel_file_manager, 'Excel_file_manager')

  static enum_map: Map<number, string> = new Map<number, string>()

  static getTypeNameFirstChatToUpCase(value: number) {
    return DataSourceIdEnum.enum_map.get(value).replace(/^./, (value) => {
      return value.toUpperCase()
    })
  }

  static getTypeName(value: number) {
    return DataSourceIdEnum.enum_map.get(value)
    // return DataSourceIdEnum.enum_map.get(value) || DataSourceIdEnum.mysqlEnum.name
  }

  static isYyHive2(value) {
    return this.yyHive2 === value
  }

  static isGushu(value) {
    return this.gushu === value
  }

  static isOracle(value) {
    return this.oracle === value
  }

  static isSqlServer(value) {
    return this.sqlServer === value
  }

  static isMongoDB(value) {
    return this.mongoDB === value
  }

  static isHbase(value) {
    return this.hbase === value
  }

  static isMysqlIm(value) {
    return this.mysqlIm === value
  }

  static isOracleIm(value) {
    return this.oracleIm === value
  }

  static isPostgreSqlIm(value) {
    return this.postgreSqlIm === value
  }

  static isRealAccessIm(value) {
    return [this.mysqlIm, this.oracleIm, this.postgreSqlIm].includes(value)
  }

  static isHdfs(value) {
    return this.hdfs === value
  }

  static isSimpleRdb(value) {
    return this.isMappingRdb(value) && !this.isRealAccessIm(value)
  }

  static isMappingRdb(value) {
    return [
      DataSourceIdEnum.dataFuse,
      DataSourceIdEnum.db2,
      DataSourceIdEnum.greenPlum,
      // DataSourceIdEnum.hbase,
      DataSourceIdEnum.impala,
      DataSourceIdEnum.informix,
      DataSourceIdEnum.kingbaseAnalytics,
      DataSourceIdEnum.librA,
      // DataSourceIdEnum.mongoDB,
      DataSourceIdEnum.mysql,
      DataSourceIdEnum.oracle,
      DataSourceIdEnum.postgreSql,
      DataSourceIdEnum.sqlServer,
      DataSourceIdEnum.emr,
      DataSourceIdEnum.kingbase,
      DataSourceIdEnum.kingbaseV8,
      DataSourceIdEnum.hwHive,
      DataSourceIdEnum.gbase,
      DataSourceIdEnum.gbase8t,
      DataSourceIdEnum.yyHive,
      DataSourceIdEnum.yyHive2,
      DataSourceIdEnum.kunlun,
      DataSourceIdEnum.shenTong,
      DataSourceIdEnum.gushu,
      DataSourceIdEnum.saisiHive,
      DataSourceIdEnum.dameng,
      DataSourceIdEnum.commonJdbc,
      DataSourceIdEnum.odps,
      DataSourceIdEnum.odpsTunnel
    ].includes(value) || this.isRealAccessIm(value)
  }

  // 不能更改，数据源管理页面有使用
  static isRdb(value) {
    return [...this.rdbCommon, ...this.hiveCommon].includes(value)
  }

  static isFileSystems(value) {
    return this.fileSystems.includes(value) || this.isTextFileManage(value)
  }

  static isFtpSftpFile(value) {
    return this.ftpSftpFile === value
  }

  static isFileTransfer(value) {
    return this.fileTransfer.includes(value) || this.isFile(value)
  }

  static isFileManages(value) {
    return [DataSourceIdEnum.FILE_MANAGER, DataSourceIdEnum.Excel_file_manager].includes(value)
  }

  static isBitsFlow(value) {
    return this.bitsFlow === value
  }

  static isEs(value) {
    return this.elasticSearch === value
  }

  static isKafka(value) {
    return this.kafka === value
  }

  static isRocketMq(value) {
    return this.rocketMq === value
  }

  static isExcel(value) {
    return this.excel === value
  }

  static isFile(value) {
    return this.file === value
  }

  static isLocal(value) {
    return this.local === value
  }

  static isFileManage(value) {
    return [this.FILE_MANAGER, this.Excel_file_manager].includes(value)
  }

  static isTextFileManage(value) {
    return [this.FILE_MANAGER].includes(value)
  }

  static isExcelFileManage(value) {
    return [this.Excel_file_manager].includes(value)
  }

  static isSftp(value) {
    return this.sftp === value
  }

  static isFtp(value) {
    return this.ftp === value
  }

  static isFtpOrSftp(value) {
    return [this.ftp, this.sftp].includes(value)
  }

  static isInformix(value) {
    return this.informix === value
  }

  static isPostgreSql(value) {
    return this.postgreSql === value
  }

  // 之前首页不展示，现在首页展示了注释掉
  // static isHomeSourceNoShow(val) {
  //   return [this.ftp, this.sftp, this.hdfs].includes(val)
  // }

  // 根据dsTypeId，获取查询时应该使用的dsTypeId
  static getQueryConnectionsDsTypeId(dsTypeId: number) {
    if (dsTypeId === this.mysqlIm) {
      return this.mysql
    } else if (dsTypeId === this.oracleIm) {
      return this.oracle
    } else if (dsTypeId === this.postgreSqlIm) {
      return this.postgreSql
    }
    return dsTypeId
  }

  // 不需要查询driverClass的数据源类型
  static readonly noSqlDBs: number[] = [
    DataSourceIdEnum.hbase,
    DataSourceIdEnum.mongoDB
  ]

  static readonly fileSystems: number[] = [
    DataSourceIdEnum.ftp,
    DataSourceIdEnum.sftp,
    DataSourceIdEnum.hdfs,
    DataSourceIdEnum.local
  ]

  static readonly fileTransfer: number[] = [
    DataSourceIdEnum.ftpSftpFile
  ]

  // 不需要查询driverClass的数据源类型
  static readonly noNeedDriver: number[] = [
    DataSourceIdEnum.hbase,
    DataSourceIdEnum.kafka,
    DataSourceIdEnum.file,
    DataSourceIdEnum.mongoDB,
    DataSourceIdEnum.odpsTunnel,
    DataSourceIdEnum.excel,
    DataSourceIdEnum.elasticSearch,
    DataSourceIdEnum.rocketMq,
    DataSourceIdEnum.oss,
    DataSourceIdEnum.hdfsFile,
    DataSourceIdEnum.ftpSftpFile,
    DataSourceIdEnum.sftp,
    DataSourceIdEnum.ftp,
    DataSourceIdEnum.hdfs,
    DataSourceIdEnum.local
  ]

  // 使用mysql页面的数据源类型
  static readonly rdbCommon: number[] = [
    DataSourceIdEnum.db2,
    DataSourceIdEnum.greenPlum,
    // DataSourceIdEnum.impala,
    DataSourceIdEnum.kingbaseAnalytics,
    DataSourceIdEnum.mysql,
    // DataSourceIdEnum.postgreSql,
    DataSourceIdEnum.sqlServer,
    DataSourceIdEnum.kingbase,
    DataSourceIdEnum.kingbaseV8,
    DataSourceIdEnum.gbase,
    DataSourceIdEnum.gbase8t,
    DataSourceIdEnum.kunlun,
    DataSourceIdEnum.shenTong,
    DataSourceIdEnum.dameng,
    DataSourceIdEnum.dataFuse,
    DataSourceIdEnum.mysqlIm
  ]

  // 使用yoyohiv页面的数据源类型
  static readonly hiveCommon: number[] = [
    DataSourceIdEnum.yyHive,
    DataSourceIdEnum.yyHive2,
    DataSourceIdEnum.saisiHive
  ]
}

for (const key in DataSourceIdEnum) {
  if (DataSourceIdEnum[key] instanceof EnumNumVO) {
    DataSourceIdEnum.enum_map.set(DataSourceIdEnum[key].value, DataSourceIdEnum[key].name)
  }
}
