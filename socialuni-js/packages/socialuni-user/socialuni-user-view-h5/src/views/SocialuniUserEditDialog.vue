<template>
  <q-dialog ref="userEditDialog" title="编辑用户信息" width="500px" :confirm="socialuniUserEditViewService.saveUser()">
    <socialuni-user-edit-view></socialuni-user-edit-view>
  </q-dialog>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch} from 'vue-facing-decorator'
import SButton from "qing-ui-h5/src/components/QComponents/QButton.vue";
import QDialog from "qing-ui-h5/src/components/QComponents/QDialog.vue";
import socialuniUserEditViewService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewService";
import SocialuniUserEditView from "./SocialuniUserEditView.vue";

@Component({
  components: {SocialuniUserEditView, QDialog, SButton}
})
export default class SocialuniUserEditDialog extends Vue {
  get socialuniUserEditViewService() {
    return socialuniUserEditViewService
  }
}
</script>
