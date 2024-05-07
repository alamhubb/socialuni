import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import SocialuniLoginFormDataVO from "../model/SocialuniLoginFormDataVO";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import SocialuniLoginFormService from "./SocialuniLoginFormService";

interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginFormServiceUni extends SocialuniLoginFormService {

}
