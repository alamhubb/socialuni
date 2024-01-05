<template>
  <q-dialog ref="userEditDialog" title="编辑用户信息" width="500px"
            :confirm="async ()=>{await socialuniUserEditViewService.saveUser()}">
    <socialuni-user-edit-view></socialuni-user-edit-view>
  </q-dialog>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch} from 'vue-facing-decorator'
import SButton from "qing-ui-h5/src/components/QComponents/QButton.vue";
import QDialog from "qing-ui-h5/src/components/QComponents/QDialog.vue";
import SocialuniUserEditView from "./SocialuniUserEditView.vue";
import socialuniUserEditViewDialogService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewDialogService";
import socialuniUserEditViewService from "socialuni-user-sdk/src/logic/socialuniUserEditViewService";
import {getCurrentInstance} from "vue";

@Component({
  components: {SocialuniUserEditView, QDialog, SButton}
})
export default class SocialuniUserEditDialog extends Vue {
  created() {
    socialuniUserEditViewDialogService.initService(getCurrentInstance())
  }

  open() {
    socialuniUserEditViewDialogService.open()
  }

  get socialuniUserEditViewService() {
    return socialuniUserEditViewService
  }
}
</script>
