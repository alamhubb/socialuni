import DomFile from "socialuni-app-sdk/src/model/DomFile";

export interface SocialuniNativeUtilInterface {
    textCopy(copyText: string, hint?: string )

    showCopyAction(copyText: string)

    createRewardedVideoAd(adUnitId: string)

    copyLink(webUrl: string)

    upxToPx(rpx: number): number

    login(provider: any)

    checkSession(): Promise<any>

    getUserInfo(provider: any)

    getSystemInfo(): Promise<any>

    getProvider(): Promise<any>

    showLoading(loadText: string)

    hideLoading()

    actionSheet(itemList: string[])

    getFile(imgFile: DomFile)

    //选择视频

    chooseVideo(maxDuration?:number)

    //选择图片
    //选择图片
    chooseImage(count?:number)

    previewImage(index, urls: string[])

    getLocation()

    //选择图片
    takePicture()

    imgFilesCompressHandler(res)


    getImageInfo(filePath: string)

    install(filePath: '_www/' | '_doc/' | '_documents/' | '_downloads/')

    showShareMenu()
}
