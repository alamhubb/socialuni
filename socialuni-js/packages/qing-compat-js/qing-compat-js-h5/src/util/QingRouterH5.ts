import {TypeIocService, TypeIocBean} from "typeiocjs/src/TypeIocDecorator";
import {QingRouterInterface} from "qing-compat-js/src/interface/QingRouterInterface";
import {useRouter} from "vue-router";

const map = {}

@TypeIocService
class QingRouterH5 {
    @TypeIocBean
    registryQingRouterInterface(): QingRouterInterface {
        const router = useRouter()
        return Object.assign({}, router)
    }
}
