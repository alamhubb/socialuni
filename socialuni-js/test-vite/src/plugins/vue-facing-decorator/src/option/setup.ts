import { compatibleMemberDecorator } from '../deco3/utils'
import type { OptionSetupFunction } from '../component'
import type { VueCons } from '../class'
import type { OptionBuilder } from '../optionBuilder'
import { obtainSlot } from '../slot'

export type SetupConfig = {
    setupFunction: OptionSetupFunction
}

export function decorator(setupFunction: OptionSetupFunction) {
    return compatibleMemberDecorator(function (proto: any, name: string) {
        const slot = obtainSlot(proto)
        const map = slot.obtainMap('setup')
        map.set(name, {
            setupFunction
        })
    })
}

const isPromise = (v: any): v is Promise<any> => v instanceof Promise

export function build(cons: VueCons, optionBuilder: OptionBuilder) {
    const slot = obtainSlot(cons.prototype)
    const map = slot.getMap('setup')
    if (!map || map.size === 0) {
        return
    }
    const setup: OptionSetupFunction = function (props, ctx) {

        const setupData: Record<string, any> = {};
        let promises: Promise<any>[] | null = null;
        for (const name of map.keys()) {

            const setupState = map.get(name)!.setupFunction(props, ctx)

            if (isPromise(setupState)) {
                promises ??= []
                promises.push(setupState.then((v) => {
                    setupData[name] = v
                }))
            } else {
                setupData[name] = setupState
            }
        }
        if (Array.isArray(promises)) {
            return Promise.all(promises).then(() => {
                return setupData
            })
        } else {
            return setupData
        }
    }

    optionBuilder.setup = setup
}
