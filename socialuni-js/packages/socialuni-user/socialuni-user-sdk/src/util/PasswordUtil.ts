import {JSEncrypt} from "jsencrypt";

export default class PasswordUtil {
    static async rsaEncode(publicKeyBase64: string, data: string) {
        // Encrypt with the public key...
        const encrypt = new JSEncrypt();
        encrypt.setPublicKey(publicKeyBase64);
        const encrypted = encrypt.encrypt(data);
        return encrypted
    }
}

function str2ab(str: string) {
    const buf = new ArrayBuffer(str.length);
    const bufView = new Uint8Array(buf);
    for (let i = 0, strLen = str.length; i < strLen; i++) {
        bufView[i] = str.charCodeAt(i);
    }
    return buf;
}

async function importPublicKey(publicKeyBase64: string) {
    // 将Base64编码的公钥转换为ArrayBuffer
    const publicKeyArray = str2ab(atob(publicKeyBase64));

    console.log(33333)
    console.log(publicKeyArray)
    // 导入公钥
    try {
        const res = await window.crypto.subtle.importKey(
            'spki',
            publicKeyArray,
            {
                name: 'RSA-OAEP',
                hash: 'SHA-256',
            },
            true,
            ['encrypt'],
        )
        console.log(44444444)
        console.log(res)
        return res
    } catch (e) {
        console.log(666666)
        console.log(e)
    }
}

async function encryptWithPublicKey(publicKey: CryptoKey, data: string) {
    const encodedData = new TextEncoder().encode(data);

    // 使用公钥进行加密
    const encryptedData = await window.crypto.subtle.encrypt(
        {
            name: "RSA-OAEP"
        },
        publicKey,
        encodedData
    );

    // 将加密的数据转换为Base64
    return window.btoa(String.fromCharCode(...new Uint8Array(encryptedData)));
}
