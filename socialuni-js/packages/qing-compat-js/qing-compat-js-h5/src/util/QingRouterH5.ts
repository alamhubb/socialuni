import {Service, TypeIocBean} from "typeiocjs/src/TypeIocDecorator";
import {QingRouterInterface} from "qing-compat-js/src/interface/QingRouterInterface";
import {useRouter} from "vue-router";

const map = {}

@Service
class QingRouterH5 {
    @TypeIocBean('a')
    registryQingRouterInterface(): QingRouterInterface {
        const router = useRouter()
        return Object.assign({}, router)
    }
}

