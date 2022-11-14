import SocialuniAppConfigRO from "../config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "../config/SocialuniAppMoreConfigRO";

export default class AppInitDataRO {
    imgPath: string = ''
    appConfig: SocialuniAppConfigRO = null
    appMoreConfig: SocialuniAppMoreConfigRO = null
    reportTypes: string [] = []
    onlineUsersCount: number = 0
}
