import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import {socialLocationModule} from "../store/SocialLocationModule";

export default class TalkQOFactory {
    static getTalkQueryQO(homeTabName: string, gender: string, minAge: number, maxAge: number, queryTime: Date, tagNames: string[], firstLoad: boolean){
        const talkQO = new TalkQueryVO(homeTabName, gender, minAge, maxAge, queryTime, tagNames, firstLoad)
        const district = socialLocationModule.location
        //查询使用当前的
        if (district) {
            talkQO.adCode = district.adCode
        }
        //经纬度始终使用同城的坐标
        if (socialLocationModule.cityLocation) {
            talkQO.lon = socialLocationModule.cityLocation.lon
            talkQO.lat = socialLocationModule.cityLocation.lat
        }
        return talkQO
    }
}
