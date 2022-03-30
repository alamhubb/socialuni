/* eslint-disable */
// #ifdef MP
//@ts-ignore
import COS from 'cos-wx-sdk-v5'
// #endif
// #ifdef H5
//@ts-ignore
import COS from 'cos-js-sdk-v5'
// #endif
import request from '../socialuni/plugins/http/request'
import SocialLoginRO from '../socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import OAuthUserInfoQO from '@/socialuni/model/dev/OAuthUserInfoQO'
import DomFile from '@/socialuni/model/DomFile'
import CosAuthRO from '@/socialuni/model/cos/CosAuthRO'
import UniUtil from '@/socialuni/utils/UniUtil'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import AppMsg from '@/socialuni/constant/AppMsg'
import CosUploadResult from '@/socialuni/model/cos/CosUploadResult'
import CosUtil from '@/socialuni/utils/CosUtil'

export default class TencentCosAPI {
  static testAPI (imgUrl, cosAuthRO: CosAuthRO) {
    const authKey = COS.getAuthorization({
      SecretId: cosAuthRO.credentials.tmpSecretId,
      SecretKey: cosAuthRO.credentials.tmpSecretKey,
      Method: 'GET',
      Expires: cosAuthRO.expiredTime
    })
    request.get('https://' + imgUrl + '?ci-process=detect-label', null, {
      header: {
        Authorization: 'q-sign-algorithm=sha1&q-ak=AKIDzJM9gxOoPlsmQo7pVw73kBLFtKWTGfuv&q-sign-time=1648674291;1648677891&q-key-time=1648674291;1648677891&q-header-list=&q-url-param-list=&q-signature=8cd6a65f6da107c6f34d41ea5bc47921b1dea0fc',
      }
    })
  }

  static async uploadFileAPI (imgFile: DomFile, data: CosAuthRO, cos) {
    return new Promise<CosUploadResult>(async (resolve, reject) => {
      const headers = {
        // "fileid": "bba022e9313849acafeb34fd5d5a65f5avatar.jpg"
        // 通过 imageMogr2 接口使用图片缩放功能：指定图片宽度为 200，宽度等比压缩
        'Pic-Operations':
          `{"is_pic_info": 1, "rules":[{"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0"},{"fileid": "${imgFile.fileName}!normal", "rule": "imageMogr2/thumbnail/800x/interlace/1"},{"fileid": "${imgFile.fileName}!thumbnail", "rule": "imageMogr2/thumbnail/300x/interlace/0"}]}`,
      }
      const uploadImgFile = await UniUtil.getFile(imgFile)
      console.log(cos)
      cos.putObject({
        Bucket: data.bucket,
        Region: data.region,
        Key: imgFile.src,
        Body: uploadImgFile,
        Headers: headers,
        // {"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0|imageMogr2/gravity/center/crop/100x100"},
      }, (err, data: CosUploadResult) => {
        if (!err) {
          resolve(data)
        } else {
          UniUtil.hideLoading()
          AlertUtil.error(AppMsg.uploadFailMsg)
          reject(err)
        }
      })
    })
  }
}
