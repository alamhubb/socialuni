export default class DomFile {
  //自己拼出来的
  // public src: string
  public src: string
  public fileName: string

  //小程序只有path和size
  path: string
  size: number
  //aspectRatio 自己计算出来的比例
  aspectRatio: number
  //quality 自己计算出来的压缩比
  quality: number

  //h5才有文件名称
  name: string
  //h5才有的属性
  lastModified: number
  lastModifiedDate: Date
  //h5才有的
  type: string
  //h5才有的
  webkitRelativePath: string
}
