import JsonUtils from '@/utils/JsonUtil'
import TokenUtil from '@/utils/TokenUtil'
import ErrorConst from '@/const/ErrorConst'
import CommonUtil from '@/utils/CommonUtil'

export default class ImgUtil {
  // static readonly imgUrl: string = process.env.VUE_APP_BASE + 'img/'
  static readonly uploadImgUrl: string = process.env.VUE_APP_BASE_API + 'upload/img/'

  static uploadImgNewAPI (uploadImg: string, type: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      uni.uploadFile({
        url: ImgUtil.uploadImgUrl,
        header: {
          token: TokenUtil.get()
        },
        filePath: uploadImg,
        name: 'file',
        formData: { // 后台以post方式接收
          type: type// 自己系统中的用户id
        },
        success: res => {
          if (res.statusCode === ErrorConst.success) {
            resolve(JsonUtils.jsonParse(res.data))
          } else {
            reject(JsonUtils.jsonParse(res.data))
          }
        },
        fail: res => {
          reject(res)
        }
      })
    })
  }

  static uploadImgAPI (uploadImg: string, type: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      uni.uploadFile({
        url: ImgUtil.uploadImgUrl,
        header: {
          token: TokenUtil.get()
        },
        filePath: uploadImg,
        name: 'file',
        formData: { // 后台以post方式接收
          type: type// 自己系统中的用户id
        },
        success: res => {
          if (res.statusCode === ErrorConst.success) {
            resolve(JsonUtils.jsonParse(res.data))
          } else {
            reject(JsonUtils.jsonParse(res.data))
          }
        },
        fail: res => {
          reject(res)
        }
      })
    })
  }

  static getTalkLargeImgUrl (userId: string, src: string): string {
    // 如果包含'/'则代表是新逻辑
    return src + '!normal'
  }

  static getTalkSmallImgUrl (userId: string, src: string): string {
    // 如果包含'/'则代表是新逻辑
    return src + '!thumbnail'
  }

  static getUserSmallImgUrl (src: string): string {
    return src + '!thumbnail'
  }

  static getUserLargeImgUrl (src: string): string {
    return src + '!normal'
  }

  static getTalkUploadFormat (userId: string, filePath: string): string {
    return 'user/' + userId + '/talk/normal/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
  }

  static getUserAvatarUploadFormat (userId: string, filePath: string): string {
    return 'user/' + userId + '/avatar/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
  }

  static getUserImgUploadFormat (userId: string, filePath: string): string {
    return 'user/' + userId + '/img/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
  }

  static getUserIdentityUploadFormat (userId: string, filePath: string): string {
    return 'user/' + userId + '/identity/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
  }

  static getFileSuffixName (filePath: string): string {
    //有可能存在asfasdf.fasdfasd.123123123dsfasdffasdf3213这种乱格式
    //如果不包含.则默认jpg
    if (filePath.indexOf('.') < 0) {
      return '.jpg'
    }
    //如果后缀长度大于6则默认jpg，因为图片都是最长4个，富裕1个，6个
    const fileSuffixName = filePath.substr(filePath.lastIndexOf('.'))
    if (fileSuffixName.length > 6) {
      return '.jpg'
    }
    return fileSuffixName
  }

  /* static getTalkLargeImgUrl(userId: string): string {
    return ImgUtil.imgUrl + 'talk/' + userId + '/normal/'
  }

  static getTalkSmallImgUrl(userId: string): string {
    return ImgUtil.imgUrl + 'talk/' + userId + '/thumbnail/'
  } */
}
