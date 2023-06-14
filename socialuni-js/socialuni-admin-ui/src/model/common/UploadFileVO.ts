import DomFile from '@/model/base/DomFile'

export default class UploadFileVO {
  fileType: string = null
  name: string = null
  files: DomFile[] = null

  constructor(fileType: string, fileName: string, files: DomFile[]) {
    this.fileType = fileType
    this.name = fileName
    this.files = files
  }
}
