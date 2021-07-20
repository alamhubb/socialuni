/**
 * @Author qingchi
 * @Date 2021-06-26 20:48
 * @Version 1.0
 */
import GetSystemInfoResult = UniApp.GetSystemInfoResult
import GetProviderRes = UniApp.GetProviderRes
import LoginRes = UniApp.LoginRes
import GetUserInfoRes = UniApp.GetUserInfoRes
import AppMsg from '@/const/AppMsg'
import Toast from '@/utils/Toast'
import DomFile from '@/model/DomFile'
import UUIDUtil from '@/utils/UUIDUtil'
import ImgUtil from '@/utils/ImgUtil'
import JsonUtil from '@/utils/JsonUtil'
import { systemModule } from '@/store'

export default class UniUtil {
  public static textCopy (copyText: string, hint?: string) {
    return new Promise((resolve, reject) => {
      uni.setClipboardData({
        data: copyText,
        success () {
          if (hint) {
            uni.hideToast()
            Toast.toast(hint)
          }
          resolve(null)
        },
        fail (err) {
          reject(err)
        }
      })
    })
  }

  public static createRewardedVideoAd (adUnitId: string) {
    if (systemModule.isMp) {
      //eslint-disable-next-line
      //@ts-ignore
      return uni.createRewardedVideoAd({
        adUnitId: adUnitId
      })
    } else {
      //eslint-disable-next-line
      //@ts-ignore
      return uni.createRewardedVideoAd({
        //eslint-disable-next-line
        //@ts-ignore
        adpid: adUnitId
      })
    }
  }

  static copyLink (webUrl: string) {
    return UniUtil.textCopy(webUrl, '链接已复制，可在浏览器打开')
  }

  public static upxToPx (rpx: number): number {
    return uni.upx2px(rpx)
  }

  public static login (provider: any) {
    return new Promise<LoginRes>(resolve => {
      uni.login({
        provider: provider,
        success (loginRes) {
          resolve(loginRes)
        }
      })
    })
  }

  static checkSession (): Promise<any> {
    return new Promise<any>((resolve, reject) =>
      uni.checkSession({
        success () {
          resolve(null)
        },
        fail (err) {
          reject(err)
        }
      })
    )
  }

  public static getUserInfo (provider: any) {
    return new Promise<GetUserInfoRes>(resolve => {
      uni.getUserInfo({
        provider: provider,
        success (userInfo) {
          resolve(userInfo)
        }
      })
    })
  }

  public static getSystemInfo (): Promise<GetSystemInfoResult> {
    return new Promise<any>(resolve =>
      uni.getSystemInfo({
        success: (res) => {
          resolve(res)
        }
      })
    )
  }

  public static getProvider (): Promise<GetProviderRes> {
    return new Promise<any>(resolve =>
      uni.getProvider({
        service: 'oauth',
        success: (res) => {
          resolve(res)
        }
      })
    )
  }

  public static showLoading (loadText: string) {
    uni.showLoading({ title: loadText || '' })
  }

  public static hideLoading () {
    uni.hideLoading()
  }

  public static actionSheet (itemList: string[]): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      uni.showActionSheet({
        itemList: itemList,
        success (res) {
          resolve(res.tapIndex)
        },
        fail (res) {
          reject(res.errMsg)
        }
      })
    })
  }


  //选择图片
  public static chooseImage (count = 1) {
    return new Promise<DomFile[]>((resolve, reject) => {
      uni.chooseImage({
        sourceType: ['album'],
        sizeType: ['original'],
        // sizeType: ['compressed'],
        count: count,
        success (res) {
          const imgFiles: DomFile[] = res.tempFiles as DomFile[]
          for (const imgFile of imgFiles) {
            JsonUtil.log(imgFile)
            // 不能大于10m大于10m就压缩不到100k
            // 获取压缩比
            const imgSize: number = imgFile.size
            if (imgSize / 1024 / 1024 > 10) {
              Toast.toastLong(AppMsg.imgSizeNotGt10MBMsg)
              reject(AppMsg.imgSizeNotGt10MBMsg)
            }
            let ratio: number = 100
            //如果大于100k 按照100k标准压缩
            if (imgSize > 100 * 1024) {
              //得出来100以下的压缩比
              //(imgSize / 1024)计算kb
              //100kb 除以 kb，再乘以百分数100
              ratio = Math.round(10000 / (imgSize / 1024))
            }
            imgFile.quality = ratio
            if (!systemModule.isH5) {
              UniUtil.compressImage(imgFile.path, ratio).then(res => {
                imgFile.path = res
                //计算压缩后的大小
                imgFile.size = Math.round(imgSize * ratio / 100)
              })
            }
            // 获取文件名
            uni.getImageInfo({
              src: imgFile.path,
              success: (image) => {
                imgFile.aspectRatio = image.width / image.height
                let fileName = null
                if (systemModule.isH5) {
                  fileName = imgFile.name
                } else {
                  fileName = imgFile.path
                }
                imgFile.src = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(fileName)
                resolve(imgFiles)
              }
            })
          }
        },
        fail (err) {
          reject(err)
        }
      })
    })
  }

  public static compressImage (filePath: string, quality: number): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      uni.compressImage({
        src: filePath,
        //默认最低20
        quality: Math.max(quality, 20),
        success: res => {
          resolve(res.tempFilePath)
        },
        fail: err => {
          reject(err)
        }
      })
    })
  }

  public static install (filePath: '_www/' | '_doc/' | '_documents/' | '_downloads/') {
    return new Promise<any>((resolve, reject) => {
      plus.runtime.install(filePath, {
        force: false
      }, () => {
        resolve(null)
      }, (e) => {
        reject(e)
      })
    })
  }

  static showShareMenu () {
    // #ifdef MP-QQ || MP-WEIXIN
    uni.showShareMenu({})
    // #endif
  }
}
