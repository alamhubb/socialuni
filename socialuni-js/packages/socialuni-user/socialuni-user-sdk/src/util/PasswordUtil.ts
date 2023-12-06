export default class PasswordUtil {
    static async rsaEncode(publicKeyBase64: string, data: string) {
        const publicKey = await importPublicKey(publicKeyBase64)
        const encryptedData = await encryptWithPublicKey(publicKey, data)
        return encryptedData
    }
}

async function importPublicKey(publicKeyBase64: string) {
    // 将Base64编码的公钥转换为ArrayBuffer
    const publicKeyDer = window.atob(publicKeyBase64);
    const publicKeyArray = new Uint8Array(publicKeyDer.length);
    for (let i = 0; i < publicKeyDer.length; i++) {
        publicKeyArray[i] = publicKeyDer.charCodeAt(i);
    }

    // 导入公钥
    return window.crypto.subtle.importKey(
        "spki",
        publicKeyArray,
        {
            name: "RSA-OAEP",
            hash: "SHA-256",
        },
        true,
        ["encrypt"]
    );
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
