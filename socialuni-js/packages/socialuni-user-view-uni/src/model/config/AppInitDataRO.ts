import SocialuniAppConfigRO from "./SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "./SocialuniAppMoreConfigRO";

export default class AppInitDataRO {
    imgPath: string = ''
    appConfig: SocialuniAppConfigRO = null
    appMoreConfig: SocialuniAppMoreConfigRO = null
    reportTypes: string [] = []
    onlineUsersCount: number = 0
}
