import {reactive} from "vue";
import {ComponentInternalInstance} from "@vue/runtime-core";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {SDialog} from "qing-ui-types/src/types/SocialuniUiTypes";

interface SocialuniUserEditViewServiceRefs {
    userEditDialog: SDialog
}

class  SocialuniUserEditViewDialogService extends SocialuniViewService<SocialuniUserEditViewServiceRefs>{

    initService(instance: ComponentInternalInstance) {
        super.initService(instance);
    }

    open() {
        this.$refs.userEditDialog.open()
    }

}

const socialuniUserEditViewDialogService = reactive(new SocialuniUserEditViewDialogService())
export default socialuniUserEditViewDialogService
