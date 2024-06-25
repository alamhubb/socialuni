import socialuniAdminRequest from "../socialuniAdminRequest";
import ConfigVO from "../model/config/ConfigVO";

export default class SocialuniAdminConfigAPI {

    static getAllConfigsAPI() {
        return socialuniAdminRequest.get('config/getAllConfigs')
    }

    static saveConfig(row: ConfigVO) {
        return socialuniAdminRequest.post('config/save', row)
    }
}
