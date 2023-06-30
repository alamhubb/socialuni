<template>
  <socialuni-user-edit-view></socialuni-user-edit-view>
</template>

<script lang="ts">
import {Emit, Options, Vue, Watch} from 'vue-property-decorator'
import SocialuniUserEditView from "socialuni-user-view-uni/src/views/user/SocialuniUserEditView.vue";

@Options({
  components: {SocialuniUserEditView}
})
export default class EditMineInfo extends Vue {
}
</script>
