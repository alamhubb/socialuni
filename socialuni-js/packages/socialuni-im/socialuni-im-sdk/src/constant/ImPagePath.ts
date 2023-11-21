import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {SocialuniImPagePathInterface} from "./SocialuniImPagePathInterface";


const modules = import.meta.globEager('../../../socialuni-im-*-sdk/src/constant/SocialuniImPagePath.ts')

const imPagePath: SocialuniImPagePathInterface = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class ImPagePath {

    static get imPagePath() {
        return imPagePath;
    }

}
