import DomFile from "qing-compat-js/src/model/DomFile";

const hasOwnProperty = Object.prototype.hasOwnProperty

export default class FileUtilH5 {
    public static fileSetPath(file: DomFile): DomFile {
        let filePath
        Object.defineProperty(file, 'path', {
            get() {
                filePath = filePath || FileUtilH5.fileToUrl(file)
                return filePath
            }
        })
        return file
    }

    static hasOwn(obj, key) {
        return hasOwnProperty.call(obj, key)
    }

    /**
     * 从本地file或者blob对象创建url
     * @param {Blob|File} file
     * @return {string}
     */
    static fileToUrl(file) {
        const files = {}
        for (const key in files) {
            if (this.hasOwn(files, key)) {
                const oldFile = files[key]
                if (oldFile === file) {
                    return key
                }
            }
        }
        var url = (window.URL || window.webkitURL).createObjectURL(file)
        files[url] = file
        return url
    }
}
