import DomFile from "../model/DomFile";
import ToastUtil from "socialuni-util/src/util/ToastUtil";
import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import UUIDUtil from "src/util/UUIDUtil";
import UniappInner from "./UniappInner";

let imageInput

export default class UniappH5 {
    public static chooseImage(count,
                              // sizeType,
                              sourceType = ['album', 'camera'],
                              extension = []) {
        return new Promise<DomFile[]>((resolve, reject) => {
            if (imageInput) {
                document.body.removeChild(imageInput)
                imageInput = null
            }
            imageInput = UniappInner.createFileInput({
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
                    let filePath
                    Object.defineProperty(file, 'path', {
                        get() {
                            filePath = filePath || UniappInner.fileToUrl(file)
                            return filePath
                        }
                    })
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
