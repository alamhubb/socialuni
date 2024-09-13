function logged(target, {kind, name}) {
    return function (initialValue) {
        console.log(target)   //undefined
        console.log(kind)   //field
        console.log(name)   //x
        console.log(initialValue) //1
        return 66;
    };
}

class C {
    @logged x = 1;
}

const newc = new C()


console.log(ts.version);  // 5.4.5
console.log(newc.x); //66
