const ary = []
const ary1 = []
for (let i = 97; i <= 122; i++) {
    ary.push(String.fromCharCode(i).repeat(3));
    ary1.push(String.fromCharCode(i).repeat(3));
}
for (let i = 1; i < 10; i++) {
    ary1.push(String(i).repeat(3));
}
for (let i = 2; i < 9; i++) {
    ary1.push(String(i - 1) + String(i) + String(i + 1));
}
console.log(ary.length)
console.log(ary)
console.log(ary1)
