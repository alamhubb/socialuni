import FileUtilH5 from "./FileUtilH5";
import InputUtilH5 from "./InputUtilH5";
import DomFile from "@socialuni/socialuni-native-util/src/model/DomFile";

let imageInput

export default class IMgUtilH5 {
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
