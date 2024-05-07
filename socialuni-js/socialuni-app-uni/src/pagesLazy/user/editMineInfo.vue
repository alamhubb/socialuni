<template>
  <socialuni-user-edit-view></socialuni-user-edit-view>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import SocialuniUserEditView from "socialuni-user-view-uni/src/views/user/SocialuniUserEditView.vue";

@toNative
@Component({
  components: {SocialuniUserEditView}
})
export default class EditMineInfo extends Vue {
}
</script>
