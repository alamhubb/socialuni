const MIMEType = {
    /**
     * 关于图片常见的MIME类型
     */
    image: {
        jpg: 'jpeg',
        jpe: 'jpeg',
        pbm: 'x-portable-bitmap',
        pgm: 'x-portable-graymap',
        pnm: 'x-portable-anymap',
        ppm: 'x-portable-pixmap',
        psd: 'vnd.adobe.photoshop',
        pic: 'x-pict',
        rgb: 'x-rgb',
        svg: 'svg+xml',
        svgz: 'svg+xml',
        tif: 'tiff',
        xif: 'vnd.xiff',
        wbmp: 'vnd.wap.wbmp',
        wdp: 'vnd.ms-photo',
        xbm: 'x-xbitmap',
        ico: 'x-icon'
    },
    /**
     * 关于视频常见的MIME类型
     */
    video: {
        '3g2': '3gpp2',
        '3gp': '3gpp',
        avi: 'x-msvideo',
        f4v: 'x-f4v',
        flv: 'x-flv',
        jpgm: 'jpm',
        jpgv: 'jpeg',
        m1v: 'mpeg',
        m2v: 'mpeg',
        mpe: 'mpeg',
        mpg: 'mpeg',
        mpg4: 'mpeg',
        m4v: 'x-m4v',
        mkv: 'x-matroska',
        mov: 'quicktime',
        qt: 'quicktime',
        movie: 'x-sgi-movie',
        mp4v: 'mp4',
        ogv: 'ogg',
        smv: 'x-smv',
        wm: 'x-ms-wm',
        wmv: 'x-ms-wmv',
        wmx: 'x-ms-wmx',
        wvx: 'x-ms-wvx'
    }
}

export default class InputUtilH5 {

    static updateElementStyle(element, styles) {
        for (const attrName in styles) {
            element.style[attrName] = styles[attrName]
        }
    }

    static createFileInput({count, sourceType, type, extension}) {
        const inputEl = document.createElement('input')
        inputEl.type = 'file'

        this.updateElementStyle(inputEl, {
            position: 'absolute',
            visibility: 'hidden',
            'z-index': -999,
            width: 0,
            height: 0,
            top: 0,
            left: 0
        })
        const ALL = '*'
        /**
         * 选择文件
         * chooseFile 使用后缀名
         * chooseImage、chooseVideo 使用MIME类型
         */
        inputEl.accept = extension.map(item => {
            if (type !== ALL) {
                const MIMEKey = item.replace('.', '')
                return `${type}/${MIMEType[type][MIMEKey] || MIMEKey}`
            } else {
                return item.indexOf('.') === 0 ? item : `.${item}`
            }
        }).join(',')

        if (count > 1) {
            inputEl.multiple = 'multiple'
        }

        // 经过测试，仅能限制只通过相机拍摄，不能限制只允许从相册选择。
        if (sourceType.length === 1 && sourceType[0] === 'camera') {
            inputEl.capture = 'camera'
        }

        return inputEl
    }

}
