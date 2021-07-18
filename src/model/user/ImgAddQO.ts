import DomFile from '@/model/DomFile'

export default class ImgAddQO {
  //自己拼出来的
  public src: string = null
  //小程序只有path和size
  size: number = null
  //aspectRatio 自己计算出来的比例
  aspectRatio: number = null
  //quality 自己计算出来的压缩比
  quality: number = null

  constructor (domFile: DomFile) {
    this.src = domFile.src
    this.size = domFile.size
    this.aspectRatio = domFile.aspectRatio
    this.quality = domFile.quality
  }
}
