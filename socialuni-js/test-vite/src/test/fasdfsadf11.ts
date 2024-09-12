import ts from 'typescript'

function logged(target, {kind, name}) {
    return function (initialValue) {
        console.log(target)   //66
        console.log(kind)   //66
        console.log(name)   //66
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
