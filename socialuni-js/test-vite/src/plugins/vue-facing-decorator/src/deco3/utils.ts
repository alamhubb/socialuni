import { VueCons } from '../class'
import { obtainSlot } from '../slot'
export const Compatible: {
    fakePrototype?: any,

} = {

}
export function compatibleClassDecorator(deco: Function) {
    return function (cons: VueCons, ctx?: ClassDecoratorContext) {
        if (ctx) {//stage 3 arg is constructor, ctx is ClassDecoratorContext
            console.log('zhiixngle xtc')
            console.log(ctx.kind)
            if (ctx.kind !== 'class') {
                throw 'deco stage 3 class'
            }
            const proto = Compatible.fakePrototype ??= {}
            const slot = obtainSlot(proto)
            delete Compatible.fakePrototype

            obtainSlot(cons.prototype, slot)

            console.log('zhixing ret')
            console.log(deco)
            console.log(cons)
            const ret = deco(cons)
            console.log('get ret')
            console.log(ret)

            return ret
        }
        else {//stage 2 arg is constructor


            console.log('zhixing ret')
            console.log(deco)
            console.log(cons)
            console.log('get ret')
            const ret = deco(cons)
            console.log('get ret')
            console.log(ret)

            return ret
        }
    }
}

export function compatibleMemberDecorator(deco: Function) {
    return function (protoOrValue: any, nameOrCtx: ClassMemberDecoratorContext | string) {
        if (typeof nameOrCtx === 'object') {//stage 3 arg is value, ctx is ClassMemberDecoratorContext
            const ctx = nameOrCtx
            const value = protoOrValue
            const proto = Compatible.fakePrototype ??= {};
            proto[ctx.name!] = value
            return deco(proto, ctx.name)
        } else { //stage 2 arg is prototype, ctx is name stirng
            const name = nameOrCtx
            const proto = protoOrValue
            return deco(proto, name)
        }
    }
}
