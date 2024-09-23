//注入的是 qingRouterInterface
//实际注入的请 qingRouterH5和qingRouterUni


//用户使用的是 qingRouter

import {TypeIocResource, TypeIocService} from "typeiocjs/src/TypeIocDecorator";
import {QingRouterInterface} from "../interface/QingRouterInterface";

@TypeIocService
class QingRouterUtilNew {
    @TypeIocResource('dada')
    readonly static qingRouter: QingRouterInterface
}


export const qingRouter = QingRouterUtilNew.qingRouter
