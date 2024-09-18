<template>
  <div class="row-col-center mb-smm">
    <q-icon class="color-sub mr-xs" icon="mdi-school" size="12"/>
    学校名称：
    <div v-if="mineUserExpandDetail?.schoolName" @click="openSetSchoolDialog">
      {{ mineUserExpandDetail?.schoolName }}
    </div>
    <div v-else class="q-tag" @click="openSetSchoolDialog">设置大学名称</div>
  </div>

  <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>
</template>


<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import UserSchoolEditDialog from "./src/views/user/UserSchoolEditDialog.vue";


@Component({
  components: {QIcon, UserSchoolEditDialog}
})
class SocialuniUserSchoolInfo extends Vue {
  $refs: {
    schoolEditDialog: UserSchoolEditDialog
  }

  get mineUserExpandDetail() {
    return socialuniAppUserModule.mineUserExpandDetail
  }

  openSetSchoolDialog() {
    this.$refs.schoolEditDialog.open()
  }
}
export default toNative(SocialuniUserSchoolInfo)
</script>


<style scoped>

</style>
