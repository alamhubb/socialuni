export default class DomFile {
  //自己拼出来的
  // public src: string
  public src: string
  public url: string // 访问的url路径
  public fileName: string
  public needAuth: boolean = false
  /**
   * 文件类型: 用于做特殊处理。
   */
  public fileType: string = 'image'
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
