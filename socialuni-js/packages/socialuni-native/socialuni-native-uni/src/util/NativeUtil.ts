/**
 * @Author qingchi
 * @Date 2021-06-26 20:48
 * @Version 1.0
 */
import GetSystemInfoResult = UniApp.GetSystemInfoResult;
import GetProviderRes = UniApp.GetProviderRes;
import LoginRes = UniApp.LoginRes;
import GetUserInfoRes = UniApp.GetUserInfoRes;
import GetImageInfoSuccessData = UniApp.GetImageInfoSuccessData;
import AppMsg from "@socialuni/socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import ImgUtil from "@socialuni/socialuni-util/src/util/ImgUtil";
import ToastUtil from "./ToastUtil";
import DomFile from "@socialuni/socialuni-api-base/src/model/DomFile";
import MPUtil from "@socialuni/socialuni-app-sdk/src/util/MPUtil";
import APPUtil from "@socialuni/socialuni-app-sdk/src/util/APPUtil";


export default class NativeUtil {
    public static textCopy(copyText: string, hint: string = '已复制') {
        return new Promise((resolve, reject) => {
            uni.setClipboardData({
                data: copyText,
                success() {
                    if (hint) {
                        uni.hideToast()
                        ToastUtil.toast(hint)
                    }
                    resolve(null)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    public static showCopyAction(copyText: string) {
        NativeUtil.actionSheet(['复制']).then((index: number) => {
            if (index === 0) {
                NativeUtil.textCopy(copyText)
            }
        })
    }


    public static createRewardedVideoAd(adUnitId: string) {
        if (socialuniSystemModule.isMp) {
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

    static copyLink(webUrl: string) {
        return NativeUtil.textCopy(webUrl, '链接已复制，可在浏览器打开')
    }

    public static upxToPx(rpx: number): number {
        return uni.upx2px(rpx)
    }

    public static login(provider: any) {
        return new Promise<LoginRes>(resolve => {
            uni.login({
                provider: provider,
                success(loginRes) {
                    resolve(loginRes)
                }
            })
        })
    }

    static checkSession(): Promise<any> {
        return new Promise<any>((resolve, reject) =>
            uni.checkSession({
                success() {
                    resolve(null)
                },
                fail(err) {
                    reject(err)
                }
            })
        )
    }

    public static getUserInfo(provider: any) {
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

    public static async getFile(imgFile: DomFile) {
        return new Promise<DomFile>((resolve, reject) => {
            if (socialuniSystemModule.isMp) {
                uni.getFileSystemManager().readFile({
                    filePath: imgFile.path,
                    success: res => {
                        resolve(res.data as any)
                    },
                    fail: err => {
                        reject(err)
                    }
                })
            } else {
                resolve(imgFile)
            }
        })
    }

    //选择视频
    public static async chooseVideo(maxDuration = 60) {
        return new Promise<DomFile[]>((resolve, reject) => {
            uni.chooseVideo({
                // sourceType: ['album'],
                // sizeType: ['compressed'],
                maxDuration: maxDuration,
                success(res) {
                    const imgFiles: DomFile[] = [];
                    const imgFile: DomFile = res.tempFile as any;
                    // imgFile.src = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(fileName)
                    imgFile.fileName = imgFile?.name
                    imgFiles.push(imgFile);
                    resolve(imgFiles)
                },
                fail(err) {
                    reject(err)
                }
            })
        })

    }

    //选择图片
    //选择图片
    public static chooseImage(count = 1) {
        return new Promise<DomFile[]>((resolve, reject) => {
            uni.chooseImage({
                // sourceType: ['album'],
                sizeType: ['original'],
                // sizeType: ['compressed'],
                count: count,
                success(res) {
                    const imgFiles = NativeUtil.imgFilesCompressHandler(res)
                    resolve(imgFiles)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    public static previewImage(index, urls: string[]) {
        uni.previewImage({
            current: index,
            urls: urls
        })
    }

    /*public static getLocation() {
        return new Promise((resolve, reject) => {
            uni.getLocation({
                type: 'gcj02 ',
                success(res) {
                    //小程序，且注册了qq的地图key
                    SocialuniLocationAPI.queryLocation(new LocationQueryQO(res.latitude, res.longitude)).then((res: ResultRO<QQMapResult>) => {
                        resolve(res.data)
                    }).catch(() => {
                        reject()
                    })
                },
                fail() {
                    reject()
                }
            })
        })
    }*/

    //选择图片
    public static takePicture() {
        return new Promise<DomFile[]>((resolve, reject) => {
            uni.chooseImage({
                sourceType: ['camera'],
                sizeType: ['original'],
                // sizeType: ['compressed'],
                count: 1,
                success(res) {
                    const imgFiles = NativeUtil.imgFilesCompressHandler(res)
                    resolve(imgFiles)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    private static async imgFilesCompressHandler(res) {
        const imgFiles: DomFile[] = res.tempFiles as DomFile[]
        const tempFilePaths: string[] = res.tempFilePaths as string[]
        for (let i = 0; i < imgFiles.length; i++) {
            const imgFile = imgFiles[i]
            // 不能大于10m大于10m就压缩不到100k
            // 获取压缩比
            const imgSize: number = imgFile.size
            if (imgSize / 1024 / 1024 > 50) {
                ToastUtil.error(AppMsg.imgSizeNotGt50MBMsg)
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
            const image = await NativeUtil.getImageInfo(imgFile.path)
            // 获取文件名
            imgFile.aspectRatio = image.width / image.height
            let fileName = null
            if (socialuniSystemModule.isH5) {
                fileName = imgFile.name
            } else {
                fileName = tempFilePaths[i]
            }
            imgFile.src = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(fileName)
            console.log(imgFile.src)
            imgFile.fileName = imgFile.src
        }
        return imgFiles
    }


    public static getImageInfo(filePath: string) {
        return new Promise<GetImageInfoSuccessData>((resolve, reject) => {
            // 获取文件名
            uni.getImageInfo({
                src: filePath,
                success: (image) => {
                    resolve(image)
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
        uni.showShareMenu()
        // #endif
    }
}
