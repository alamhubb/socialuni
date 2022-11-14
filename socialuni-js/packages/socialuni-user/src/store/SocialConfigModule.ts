import {Pinia, Store} from "pinia-class-component"
import SocialuniAppConfigRO from "socialuni-common/src/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "socialuni-common/src/config/SocialuniAppMoreConfigRO";

@Store
export default class SocialConfigModule extends Pinia {
    // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
    appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
    appMoreConfig: SocialuniAppMoreConfigRO = new SocialuniAppMoreConfigRO()
}
