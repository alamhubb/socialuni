<template>
  <socialuni-user-edit-view></socialuni-user-edit-view>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import SocialuniUserEditView from "socialuni-user-view-uni/src/views/user/SocialuniUserEditView.vue";


@Component({
  components: {SocialuniUserEditView}
})
class EditMineInfo extends Vue {
}
export default toNative(EditMineInfo)
</script>
