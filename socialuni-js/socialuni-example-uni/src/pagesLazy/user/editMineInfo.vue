<template>
  <socialuni-user-edit-view></socialuni-user-edit-view>
</template>

<script lang="ts">
import {Emit, Options, Vue, Watch} from 'vue-facing-decorator'
import SocialuniUserEditView from "@socialuni/socialuni-user-view-uni/src/views/user/SocialuniUserEditView.vue";

@Component({
  components: {SocialuniUserEditView}
})
export default class EditMineInfo extends Vue {
}
</script>
