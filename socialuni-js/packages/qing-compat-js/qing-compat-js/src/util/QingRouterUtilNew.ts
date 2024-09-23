//注入的是 qingRouterInterface
//实际注入的请 qingRouterH5和qingRouterUni
//用户使用的是 qingRouter
import typeIocContainer from "typeiocjs/src/TypeIocContainer.ts";
import {QingRouterInterface} from "qing-compat-js/src/interface/QingRouterInterface.ts";

export let qingRouter:QingRouterInterface = null

setTimeout(() => {
    qingRouter = typeIocContainer.containerGet('QingRouterInterface')
})

