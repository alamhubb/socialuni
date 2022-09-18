import SocialuniAppMoreConfigRO from "@/socialuni/model/config/SocialuniAppMoreConfigRO";
import SocialuniAppConfigRO from "@/socialuni/model/config/SocialuniAppConfigRO";

export default class AppInitDataRO {
  imgPath: string = ''
  appConfig: SocialuniAppConfigRO = null
  appMoreConfig: SocialuniAppMoreConfigRO = null
  reportTypes: string [] = []
  onlineUsersCount: number = 0
}
