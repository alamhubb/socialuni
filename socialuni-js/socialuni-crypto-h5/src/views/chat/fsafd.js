const ary = []
const ary1 = []
const res = []
for (let i = 97; i <= 122; i++) {
    ary.push(String.fromCharCode(i).repeat(1));
    ary.push(String.fromCharCode(i).repeat(2));
    ary.push(String.fromCharCode(i).repeat(3));
    ary.push(String.fromCharCode(i).repeat(4));
    ary1.push(String.fromCharCode(i).repeat(1));
    ary1.push(String.fromCharCode(i).repeat(2));
    ary1.push(String.fromCharCode(i).repeat(3));
}
for (let i = 1; i < 10; i++) {
    ary1.push(String(i).repeat(2));
    ary1.push(String(i).repeat(3));
}
for (let i = 2; i < 10; i++) {
    ary1.push(String(i - 1) + String(i));
}
for (let i = 2; i < 9; i++) {
    ary1.push(String(i - 1) + String(i) + String(i + 1));
}

for (const aryElement of ary) {
    for (const ary1Element of ary1) {
        if (aryElement !== ary1Element) {
            const resStr = aryElement + ary1Element
            if (resStr.length > 2) {
                res.push(resStr)
            }
        }
    }
}

console.log(res.length)

let uniqueArray = [...new Set(res)];
let ofdsaf = []
let objfad = {}
for (const uniqueArrayElement of uniqueArray) {
    if (uniqueArrayElement){
        ofdsaf.push(`INSERT INTO s_deploy_project_temp_name (sequence_num, status, name) VALUES (0, '正常', ${uniqueArrayElement});`)
        objfad[uniqueArrayElement] = uniqueArrayElement
    }
}

const fs = require('fs');

// 要写入的内容
const content = JSON.stringify(ofdsaf);

// 目标文件的路径
const filePath = './file.json';

// 使用fs.writeFile方法写入内容
fs.writeFile(filePath, content, 'utf8', function(err) {
    if (err) {
        console.error(err);
        return;
    }
    console.log('文件已被保存');
});
