<template>
  <q-dialog ref="userEditDialog" title="编辑用户信息" width="500px"
            :confirm="async ()=>{await socialuniUserEditViewDialogService.saveUser()}">
    <socialuni-user-edit-view></socialuni-user-edit-view>
  </q-dialog>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import SButton from "qing-compat-js-ui/src/components/QButton.vue";
import QDialog from "qing-compat-js-ui-h5/src/components/QDialog.vue";
import SocialuniUserEditView from "./SocialuniUserEditView.vue";
import socialuniUserEditViewDialogService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewDialogService";
import {getCurrentInstance} from "vue";

@toNative
@Component({
  components: {SocialuniUserEditView, QDialog, SButton}
})
export default class SocialuniUserEditDialog extends Vue {
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
</script>
