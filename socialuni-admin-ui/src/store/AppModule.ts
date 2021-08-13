import { VuexModule, Module, Action } from 'vuex-class-modules'
import LoginAPI from '@/api/LoginAPI'
import AuthUtil from '@/utils/AuthUtil'

@Module({ generateMutationSetters: true })
export default class AppModule extends VuexModule {

    @Action
    appInit () {
        // 初始化数据看一下这些请求是否可以合并 登陆之后也要链接websocket
        appModule.initGlobalDataLoadAPI()
        // 测试时使用，生产时在talk也ready后查询
        // appModule.initGlobalDataReadyAPI()
        // 不为app平台在这里设置platform否则在systemInfo中设置
    }
}
