import JsonUtils from 'socialuni-util/src/util/ObjectUtil'
import CommonUtil from 'socialuni-util/src/util/CommonUtil'
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import {socialuniUserModule} from "socialuni-sdk/src/store/store";

export default class SocialuniImgUtil {
    // static readonly imgUrl: string = process.env.VUE_APP_BASE + 'img/'
    static readonly uploadImgUrl: string = ''

    // static readonly uploadImgUrl: string = process.env.VUE_APP_BASE_API + 'upload/img/'

    static uploadImgNewAPI(uploadImg: string, type: string): Promise<any> {
       /* return new Promise<any>((resolve, reject) => {
            uni.uploadFile({
                url: ImgUtil.uploadImgUrl,
                header: {
                    token: socialuniUserModule.token
                },
                filePath: uploadImg,
                name: 'file',
                formData: { // 后台以post方式接收
                    type: type// 自己系统中的用户id
                },
                success: res => {
                    if (res.statusCode === ErrorConst.success) {
                        resolve(JsonUtils.toParse(res.data))
                    } else {
                        reject(JsonUtils.toParse(res.data))
                    }
                },
                fail: res => {
                    reject(res)
                }
            })
        })*/
    }

    static uploadImgAPI(uploadImg: string, type: string): Promise<any> {
        /*return new Promise<any>((resolve, reject) => {
            uni.uploadFile({
                url: ImgUtil.uploadImgUrl,
                header: {
                    token: socialuniUserModule.token
                },
                filePath: uploadImg,
                name: 'file',
                formData: { // 后台以post方式接收
                    type: type// 自己系统中的用户id
                },
                success: res => {
                    if (res.statusCode === ErrorConst.success) {
                        resolve(JsonUtils.toParse(res.data))
                    } else {
                        reject(JsonUtils.toParse(res.data))
                    }
                },
                fail: res => {
                    reject(res)
                }
            })
        })*/
    }

    static getTalkLargeImgUrl(userId: string, src: string): string {
        // 如果包含'/'则代表是新逻辑
        return src + '!normal'
    }

    static getTalkSmallImgUrl(userId: string, src: string): string {
        // 如果包含'/'则代表是新逻辑
        return src + '!thumbnail'
    }

    static getUserSmallImgUrl(src: string): string {
        return src + '!thumbnail'
    }

    static getUserLargeImgUrl(src: string): string {
        return src + '!normal'
    }

    static getTalkUploadFormat(userId: string, filePath: string): string {
        return 'user/' + userId + '/home/normal/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
    }

    static getUserAvatarUploadFormat(userId: string, filePath: string): string {
        return 'user/' + userId + '/avatar/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
    }

    static getUserImgUploadFormat(userId: string, filePath: string): string {
        return 'user/' + userId + '/img/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
    }

    static getUserIdentityUploadFormat(userId: string, filePath: string): string {
        return 'user/' + userId + '/identity/' + CommonUtil.getUUID() + ImgUtil.getFileSuffixName(filePath)
    }



    /* static getTalkLargeImgUrl(userId: string): string {
      return ImgUtil.imgUrl + 'home/' + userId + '/normal/'
    }

    static getTalkSmallImgUrl(userId: string): string {
      return ImgUtil.imgUrl + 'home/' + userId + '/thumbnail/'
    } */
}
