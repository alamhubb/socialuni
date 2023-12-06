import SocialuniAppConfigRO from "./SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "./SocialuniAppMoreConfigRO";

export default class AppInitDataRO {
    imgPath: string = ''
    appConfig: SocialuniAppConfigRO = null
    appMoreConfig: SocialuniAppMoreConfigRO = null
    reportTypes: string [] = []
    publicKey: string = null
    onlineUsersCount: number = 0
}
