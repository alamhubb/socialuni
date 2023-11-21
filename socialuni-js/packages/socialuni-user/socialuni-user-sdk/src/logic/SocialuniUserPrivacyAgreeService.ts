import {SocialuniViewServiceInterface} from "socialuni/src/interface/SocialuniViewServiceInterface";

export default class SocialuniUserPrivacyAgreeService implements SocialuniViewServiceInterface {
    // 同意协议
    contractChecked = false

    contractCheckedChange(e) {
        this.contractChecked = !this.contractChecked
    }
}
