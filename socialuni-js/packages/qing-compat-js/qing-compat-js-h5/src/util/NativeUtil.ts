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
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import AppMsg from "socialuni-constant/constant/AppMsg";
import ImgUtilH5 from "./ImgUtilH5";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import ImgUtil from "qing-util/src/util/ImgUtil";


export default class NativeUtil {
    public static textCopy(copyText: string, hint: string = '已复制') {
        return new Promise((resolve, reject) => {
            uni.setClipboardData({
                data: copyText,
                success() {
                    if (hint) {
                        uni.hideToast()
                        QingAppUtil.ToastUtil.toast(hint)
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
        QingAppUtil.NativeUtil.actionSheet(['复制']).then((index: number) => {
            if (index === 0) {
                QingAppUtil.NativeUtil.textCopy(copyText)
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
        return QingAppUtil.NativeUtil.textCopy(webUrl, '链接已复制，可在浏览器打开')
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
        // uni.showLoading({title: loadText || ''})
    }

    public static hideLoading() {
        // uni.hideLoading()
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
            resolve(imgFile)
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
    public static chooseImage(count = 1) {
        return ImgUtilH5.chooseImage(count).then(async (res) => {
            const imgFiles = await ImgUtilH5.imgFilesCompressHandler(res)
            return imgFiles
        })
    }

    //选择图片
    public static takePicture() {
        return new Promise<DomFile[]>((resolve, reject) => {
            uni.chooseImage({
                sourceType: ['camera'],
                sizeType: ['original'],
                // sizeType: ['compressed'],
                count: 1,
                success(res) {
                    const imgFiles = ImgUtilH5.imgFilesCompressHandler(res)
                    resolve(imgFiles)
                },
                fail(err) {
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
    }
}
