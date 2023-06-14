export default class ImgUtil{

    static getFileSuffixName(filePath: string): string {
        //有可能存在asfasdf.fasdfasd.123123123dsfasdffasdf3213这种乱格式
        //如果不包含.则默认jpg
        if (filePath.indexOf('.') < 0) {
            return '.jpg'
        }
        //如果后缀长度大于6则默认jpg，因为图片都是最长4个，富裕1个，6个
        const fileSuffixName = filePath.substr(filePath.lastIndexOf('.'))
        //有可能存在asfasdf.fasdfasd.这种乱格式
        if (fileSuffixName.length > 6 || fileSuffixName.length < 2) {
            return '.jpg'
        }
        return fileSuffixName
    }

}
