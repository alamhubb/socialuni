import GetSystemInfoResult = UniApp.GetSystemInfoResult
import GetProviderRes = UniApp.GetProviderRes
import LoginRes = UniApp.LoginRes
import {Provider} from '@/const/ProviderType'
import GetUserInfoRes = UniApp.GetUserInfoRes
import ImgFileVO from '@/model/ImgFileVO'
import AppMsg from '@/const/AppMsg'
import {systemModule} from '@/plugins/store'
import Toast from "@/utils/Toast";
import Alert from "./Alert";
import CommonUtil from "@/utils/CommonUtil";

export default class UniUtil {
  public static textCopy(copyText: string, hint?: string) {
    return new Promise((resolve, reject) => {
      uni.setClipboardData({
        data: copyText,
        success() {
          if (hint) {
            uni.hideToast()
            Toast.toast(hint)
          }
          resolve(null)
        },
        fail(err) {
          reject(err)
        }
      })
    })
  }

  public static createRewardedVideoAd(adUnitId: string) {
    if (systemModule.isMp) {
      return uni.createRewardedVideoAd({
        adUnitId: adUnitId
      })
    } else {
      return uni.createRewardedVideoAd({
        //eslint-disable-next-line
        //@ts-ignore
        adpid: adUnitId
      })
    }
  }

  static copyLink(webUrl: string) {
    return UniUtil.textCopy(webUrl, '链接已复制，可在浏览器打开')
  }

  public static upxToPx(rpx: number): number {
    return uni.upx2px(rpx)
  }

  public static login(provider: Provider) {
    return new Promise<LoginRes>(resolve => {
      uni.login({
        provider: provider,
        success(loginRes) {
          resolve(loginRes)
        }
      })
    })
  }

  public static getUserInfo(provider: Provider) {
    return new Promise<GetUserInfoRes>(resolve => {
      uni.getUserInfo({
        provider: provider,
        success(userInfo) {
          resolve(userInfo)
        }
      })
    })
  }

  public static getSystemInfo(): Promise<GetSystemInfoResult> {
    return new Promise<any>(resolve =>
      uni.getSystemInfo({
        success: (res) => {
          resolve(res)
        }
      })
    )
  }

  public static getProvider(): Promise<GetProviderRes> {
    return new Promise<any>(resolve =>
      uni.getProvider({
        service: 'oauth',
        success: (res) => {
          resolve(res)
        }
      })
    )
  }

  public static showLoading(loadText: string) {
    uni.showLoading({title: loadText || ''})
  }

  public static hideLoading() {
    uni.hideLoading()
  }

  public static actionSheet(itemList: string[]): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      uni.showActionSheet({
        itemList: itemList,
        success(res) {
          resolve(res.tapIndex)
        },
        fail(res) {
          reject(res.errMsg)
        }
      })
    })
  }


  public static chooseImage(count = 1) {
    return new Promise<ImgFileVO[]>((resolve, reject) => {
      uni.chooseImage({
        sourceType: ['album'],
        sizeType: ['original'],
        // sizeType: ['compressed'],
        count: count,
        success(res) {
          const imgFiles: any = res.tempFiles
          for (const imgFile of imgFiles) {
            // 不能大于10m大于10m就压缩不到100k
            // 获取压缩比
            const imgSize: number = imgFile.size
            if (imgSize / 1024 / 1024 > 10) {
              Alert.error(AppMsg.imgSizeNotGt10MBMsg)
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
            if (systemModule.isNH5) {
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
              }
            })
          }
          resolve(imgFiles)
        },
        fail(err) {
          reject(err)
        }
      })
    })
  }

  public static compressImage(filePath: string, quality: number): Promise<string> {
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

  public static install(filePath: '_www/' | '_doc/' | '_documents/' | '_downloads/') {
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

  static showShareMenu() {
    // #ifdef MP-QQ || MP-WEIXIN
    uni.showShareMenu({})
    // #endif
  }
}
