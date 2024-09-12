var Sf = Object.defineProperty, Tf = Object.getOwnPropertyDescriptor, hi = (e, t, n, r) => {
    for (var s = r > 1 ? void 0 : r ? Tf(t, n) : t, i = e.length - 1, o; i >= 0; i--) (o = e[i]) && (s = (r ? o(t, n, s) : o(s)) || s);
    return r && s && Sf(t, n, s), s
};

var bi = (e, t, n) => t in e ? yi(e, t, {enumerable: !0, configurable: !0, writable: !0, value: n}) : e[t] = n;

var Te = (e, t, n) => bi(e, typeof t != "symbol" ? t + "" : t, n);

let zt = class {
    constructor() {
        Te(this, "testA")
    }

    test() {
        console.log(11111)
    }
};

hi([Of(22)], zt.prototype, "testA", 2);

function Of(e) {
    return function (t, n) {
        return console.log(e), console.log(t), console.log(n), t[n] = e, t
    }
}
