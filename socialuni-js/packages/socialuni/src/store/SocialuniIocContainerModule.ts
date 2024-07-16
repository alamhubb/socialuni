import {reactive, UnwrapNestedRefs} from "vue";

class SocialuniIocContainerModule {
    private containerMap = new Map()

    registry(key: string, obj: any) {
        this.containerMap.set(key, obj)
    }

    get(key: string): any {
        return this.containerMap.get(key)
    }
}

const socialuniIocContainerModuleObj: UnwrapNestedRefs<SocialuniIocContainerModule> = reactive(new SocialuniIocContainerModule())

export const socialuniIocContainerModule = socialuniIocContainerModuleObj
