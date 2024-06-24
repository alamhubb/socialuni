import FileUtilH5 from "./FileUtilH5";
import InputUtilH5 from "./InputUtilH5";
import DomFile from "qing-compat-js/src/model/DomFile";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import ImgUtil from "qing-util/src/util/ImgUtil";

let imageInput

export default class ImgUtilH5 {
    public static chooseImage(count,
                              // sizeType,
                              sourceType = ['album', 'camera'],
                              extension = []) {
        return new Promise<DomFile[]>((resolve, reject) => {
            if (imageInput) {
                document.body.removeChild(imageInput)
                imageInput = null
            }
            imageInput = InputUtilH5.createFileInput({
                count,
                sourceType,
                extension,
                type: 'image'
            })
            document.body.appendChild(imageInput)
            imageInput.addEventListener('change', function (event) {
                const tempFiles = []
                const fileCount = event.target.files.length
                for (let i = 0; i < fileCount; i++) {
                    const file = event.target.files[i]
                    FileUtilH5.fileSetPath(file);
                    if (i < count) tempFiles.push(file)
                }
                const res = {
                    errMsg: 'chooseImage:ok',
                    get tempFilePaths() {
                        return tempFiles.map(({path}) => path)
                    },
                    tempFiles: tempFiles
                }
                resolve(res)
            })
            imageInput.click()
        })
    }

    public static async imgFilesCompressHandler(res) {
        const imgFiles: DomFile[] = res.tempFiles as DomFile[]
        for (const imgFile of imgFiles) {
            await this.setImgQualityAndAspectRatio(imgFile);
        }
        return imgFiles
    }

    public static async setImgQualityAndAspectRatio(imgFile: DomFile) {
        //设置上传信息
        this.setUploadInfo(imgFile)
        //设置压缩比
        this.setImgQuality(imgFile)
        //设置横宽比
        await this.setImgAspectRatio(imgFile)
    }


    //设置图片压缩比
    private static setImgQuality(imgFile: DomFile) {
        // 不能大于10m大于10m就压缩不到100k
        // 获取压缩比
        const imgSize: number = imgFile.size
        if (imgSize / 1024 / 1024 > 50) {
            QingAppUtil.ToastUtil.throwError(AppMsg.imgSizeNotGt50MBMsg)
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
    }

    //设置图片宽高比
    private static async setImgAspectRatio(imgFile: DomFile) {
        const image = await this.getImageInfo(imgFile.path)
        // 获取文件名
        imgFile.aspectRatio = image.width / image.height
    }

    private static setUploadInfo(imgFile: DomFile) {
        let fileName = null
        if (socialuniSystemModule.isH5) {
            fileName = imgFile.name
        } else {
            fileName = imgFile.path
        }
        imgFile.src = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(fileName)
        imgFile.fileName = imgFile.src
    }

    public static getImageInfo(filePath: string) {
        return new Promise<any>((resolve, reject) => {
            const img = new Image()
            img.onload = function () {
                resolve({
                    width: img.naturalWidth,
                    height: img.naturalHeight
                })
            }
            img.onerror = function (e) {
                reject({
                    errMsg: 'getImageInfo:fail'
                })
            }
            img.src = filePath
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
}
