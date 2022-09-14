import {Module, VuexModule} from 'vuex-class-modules'
import SocialuniAppConfigRO from "@/socialuni/model/config/SocialuniAppConfigRO";

@Module({generateMutationSetters: true})
export default class SocialConfigModule extends VuexModule {
  // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
  appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
}
