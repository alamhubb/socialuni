import {reactive, UnwrapNestedRefs} from "vue";

class SocialuniIocContainerModule {
    private containerMap = new Map()

    registry(key: string, obj: any) {
        console.log('zhuce')
        this.containerMap.set(key, obj)
    }

    get(key: string): any {
        console.log('获取')
        console.log(this.containerMap)
        return this.containerMap.get(key)
    }
}

const socialuniIocContainerModuleObj: UnwrapNestedRefs<SocialuniIocContainerModule> = reactive(new SocialuniIocContainerModule())

export const socialuniIocContainerModule = socialuniIocContainerModuleObj
