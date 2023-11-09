const crypto = require('crypto');
//Signature=md5(AppId + SignatureNonce + ServerSecret + Timestamp)
function GenerateUASignature(appId, signatureNonce, serverSecret, timeStamp){
    const hash = crypto.createHash('md5'); //规定使用哈希算法中的MD5算法
    console.log(hash)
    var str = appId + signatureNonce + serverSecret + timeStamp;
    console.log(str)
    hash.update(str);
    console.log(hash)

    const hash1 = crypto.createHash('md5'); //规定使用哈希算法中的MD5算法
    console.log(hash)
    var str = appId + signatureNonce + serverSecret + timeStamp;
    console.log(str)
    hash1.update(str);
    console.log(hash1)
    console.log(hash1.digest('base64'))
    //hash.digest('hex')表示输出的格式为16进制
    return hash.digest('hex');
}
console.log(crypto.randomBytes(8))
// var signatureNonce = crypto.randomBytes(8).toString('hex');
// var appId = 12345;
// var serverSecret = "9193cc662a4c0ec135ec71fb57194b38";
// var timeStamp = Math.round(Date.now()/1000);
// console.log(GenerateUASignature(appId, signatureNonce, serverSecret, timeStamp));
