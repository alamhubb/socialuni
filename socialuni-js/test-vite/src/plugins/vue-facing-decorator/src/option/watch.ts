import type { VueCons } from '../class'
import type { OptionBuilder } from '../optionBuilder'
import { obtainSlot } from '../slot'
import type { WatchCallback } from 'vue'
import { compatibleMemberDecorator } from '../deco3/utils'
export interface WatchConfig {
    key: string
    handler: WatchCallback,
    flush?: 'post',
    deep?: boolean,
    immediate?: boolean,
}
type Option = Omit<WatchConfig, 'handler' | 'key'>
export function decorator(key: string, option?: Option) {
    return compatibleMemberDecorator(function (proto: any, name: string) {
        const slot = obtainSlot(proto)
        const map = slot.obtainMap('watch');
        const opt = Object.assign({}, option ?? {}, {
            key: key,
            handler: proto[name]
        })
        if (map.has(name)) {
            const t = map.get(name)!
            if (Array.isArray(t)) {
                t.push(opt)
            } else {
                map.set(name, [t, opt])
            }
        }
        else {
            map.set(name, opt)
        }
    })
}

export function build(cons: VueCons, optionBuilder: OptionBuilder) {
    optionBuilder.watch ??= {}
    const slot = obtainSlot(cons.prototype)
    const names = slot.getMap('watch')
    if (!names || names.size === 0) {
        return
    }

    names.forEach((value, _name) => {
        const values = Array.isArray(value) ? value : [value]
        values.forEach(v => {
            if (!optionBuilder.watch![v.key]) {
                optionBuilder.watch![v.key] = v
            } else {
                const t = optionBuilder.watch![v.key]
                if (Array.isArray(t)) {
                    t.push(v)
                } else {
                    optionBuilder.watch![v.key] = [t, v]
                }
            }
        })
    })


}
