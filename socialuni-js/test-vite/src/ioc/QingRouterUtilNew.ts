//注入的是 qingRouterInterface
//实际注入的请 qingRouterH5和qingRouterUni


//用户使用的是 qingRouter
import {TypeIocResource, TypeIocService} from "typeiocjs/src/TypeIocDecorator";
import typeIocContainer from "typeiocjs/src/TypeIocContainer.ts";
import {QingRouterInterface} from "qing-compat-js/src/interface/QingRouterInterface.ts";

@TypeIocService
export class QingRouterUtilNew {
    @TypeIocResource
    static qingRouter: QingRouterInterface
}

export let qingRouter:QingRouterInterface = null

setTimeout(() => {
    qingRouter = typeIocContainer.containerGet('QingRouterInterface')
})

