import DomFile from '@/model/base/DomFile'

export default class FileUploadVO {
  dirId: number = null
  expireTime: Date = null
  upfiles: DomFile[] = null

  constructor(dirId?: number) {
    this.dirId = dirId || 0
  }
}
