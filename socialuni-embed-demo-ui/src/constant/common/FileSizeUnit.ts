export default class FileSizeUnit {
  static readonly Byte: number = 1
  static readonly KB: number = 1024
  static readonly MB: number = 1024 * FileSizeUnit.KB
  static readonly GB: number = 1024 * FileSizeUnit.MB
  static readonly TB: number = 1024 * FileSizeUnit.GB

  static getFileSizeUnit(fileSize: number) {
    if (fileSize > FileSizeUnit.TB) {
      return (fileSize / FileSizeUnit.TB).toFixed(2) + 'TB'
    } else if (fileSize > FileSizeUnit.GB) {
      return (fileSize / FileSizeUnit.GB).toFixed(2) + 'GB'
    } else if (fileSize > FileSizeUnit.MB) {
      return (fileSize / FileSizeUnit.MB).toFixed(2) + 'MB'
    } else if (fileSize > FileSizeUnit.KB) {
      return (fileSize / FileSizeUnit.KB).toFixed(2) + 'KB'
    } else {
      return fileSize.toFixed(2) + 'B'
    }
  }
}
