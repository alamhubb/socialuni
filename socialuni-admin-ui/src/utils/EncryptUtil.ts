import JSEncrypt from 'jsencrypt'
import CryptoJS from 'crypto-js'
import AuthUtil from '@/utils/AuthUtil'

export default class EncryptUtil {
  // 生成随机字符串,用于随机产生AES秘钥
  static randomString(n) {
    const str = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ9876543210'
    let tmp = ''
    let i = 0
    const len = str.length
    for (i = 0; i < n; i++) {
      tmp += str.charAt(Math.floor(Math.random() * len))
    }
    return tmp
  }

  /**
     * 加密
     * @param {object} data 需要加密所在的对象
     * @param {array} keys  需要加密的字段列表，必须在对象中
     * @returns {array} [RSAEncrypt,data] RSAEncrypt为RSA加密串（放入header中），data为加密后的对象
     */
  static encryptString(data, keys) {
    // 生成随机aes的key，16位
    const randomNum = EncryptUtil.randomString(16)
    const AESkey = CryptoJS.enc.Hex.parse(EncryptUtil.strToHexCharCode(randomNum))
    for (const item in data) {
      if (keys.indexOf(item) > -1) {
        // 将加密字符串用AES进行加密
        const AESEncrypt = CryptoJS.AES.encrypt(data[item], AESkey, {
          mode: CryptoJS.mode.ECB
        })
        // java需要的字符串
        // const cipherText = AESEncrypt.ciphertext.toString()
        data[item] = AESEncrypt.ciphertext.toString()
        // // 解密验证
        // const cipherTextHexStr = CryptoJS.enc.Hex.parse(data[item])
        // const aesDecrypt = CryptoJS.AES.decrypt(
        //   CryptoJS.enc.Base64.stringify(cipherTextHexStr),
        //   AESkey,
        //   {
        //     mode: CryptoJS.mode.ECB
        //   }
        // )
        // const originalText = aesDecrypt.toString(CryptoJS.enc.Utf8)
      }
    }
    // 将AESkey用rsa进行加密
    const encrypt = new JSEncrypt()
    encrypt.setPublicKey(AuthUtil.getPublicKey())
    const RSAEncrypt = encrypt.encrypt(randomNum)
    // 返回结果，RSAEncrypt放置于header中，AESEncrypt用于替换加密前的结果
    return [RSAEncrypt, data]
  }

  // 转16进制
  static strToHexCharCode(str) {
    if (str === '') return ''
    const hexCharCode = []
    for (let i = 0; i < str.length; i++) {
      hexCharCode.push(str.charCodeAt(i).toString(16))
    }
    return hexCharCode.join('')
  }
}
