<template>
  <q-dialog ref="userEditDialog" title="编辑用户信息" width="500px"
            :confirm="async ()=>{await socialuniUserEditViewDialogService.saveUser()}">
    <socialuni-user-edit-view></socialuni-user-edit-view>
  </q-dialog>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import SButton from "qingjs-ui/src/components/QButton.vue";
import QDialog from "qingjs-ui-h5/src/components/QDialog.vue";
import SocialuniUserEditView from "./SocialuniUserEditView.vue";
import socialuniUserEditViewDialogService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewDialogService";
import {getCurrentInstance} from "vue";


@Component({
  components: {SocialuniUserEditView, QDialog, SButton}
})
class SocialuniUserEditDialog extends Vue {
  created() {
    socialuniUserEditViewDialogService.initService(this)
  }

  open() {
    socialuniUserEditViewDialogService.open()
  }

  get socialuniUserEditViewDialogService() {
    return socialuniUserEditViewDialogService
  }
}
export default toNative(SocialuniUserEditDialog)
</script>
