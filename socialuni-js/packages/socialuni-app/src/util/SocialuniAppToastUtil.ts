import {UniInterface} from "../UniInterface";
import {inject} from "vue";
import SocialuniProvideKey from "../constant/SocialuniProvideKey";




export default class SocialuniAppToastUtil {
    get uniappUtil(): UniInterface {
        return inject<UniInterface>(SocialuniProvideKey.uniappUtilKey) as UniInterface
    }
}
