const ary = []
const ary1 = []
const res = []
for (let i = 97; i <= 122; i++) {
    ary.push(String.fromCharCode(i).repeat(2));
    ary1.push(String.fromCharCode(i).repeat(2));
}
for (let i = 1; i < 10; i++) {
    ary1.push(String(i).repeat(2));
}
for (let i = 2; i < 9; i++) {
    ary1.push(String(i - 1) + String(i));
}
for (const aryElement of ary) {
    for (const ary1Element of ary1) {
        if (aryElement !== ary1Element) {
            res.push(aryElement + ary1Element)
        }
    }
}

console.log(res.length)
// console.log(JSON.stringify(res))

