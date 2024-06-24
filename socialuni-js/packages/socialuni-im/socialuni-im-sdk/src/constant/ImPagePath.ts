import PlatformModuleLoadUtil from "qing-compat-js/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "./SocialuniImPagePathInterface";


const modules = import.meta.glob('../../../socialuni-im-*-sdk/src/constant/SocialuniImPagePath.ts',{eager:true})

const imPagePath: SocialuniImPagePathInterface = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class ImPagePath {

    static get imPagePath() {
        return imPagePath;
    }

}
