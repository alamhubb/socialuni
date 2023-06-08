export default class CosUploadResult {
  ETag: string = null
  Location: string = null
  UploadResult: {
    OriginalInfo: OriginalInfo
  } = null
}

class OriginalInfo {
  ETag: string = null
  Key: string = null
  Location: string = null
}
