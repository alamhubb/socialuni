import {reactive} from "vue";
import {ComponentInternalInstance} from "@vue/runtime-core";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {SDialog} from "qingjs-ui-types/src/types/SocialuniUiTypes";
import socialuniUserEditViewService from "./SocialuniUserEditViewService";

interface SocialuniUserEditViewServiceRefs {
    userEditDialog: SDialog
}

class SocialuniUserEditViewDialogService extends SocialuniViewService<SocialuniUserEditViewServiceRefs> {

    initService(instance: Vue) {
        super.initService(instance);
    }

    open() {
        console.log(this)
        this.$refs.userEditDialog.open()
    }

    closeUserEditPop() {
        // console.log(this.instance)
        // const emits = defineEmits(['close'])
        // emits('close')
        this.instance.$emit('close')
    }

    async saveUser() {
        try {
            await socialuniUserEditViewService.saveUser()
            this.closeUserEditPop()
        } catch (e) {
            console.log(e)
        }
    }
}

const socialuniUserEditViewDialogService = reactive(new SocialuniUserEditViewDialogService())
export default socialuniUserEditViewDialogService
