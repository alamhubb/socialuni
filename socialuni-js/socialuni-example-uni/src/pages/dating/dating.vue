<template>
  <socialuni-dating-view></socialuni-dating-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialuniDatingView from "@socialuni/socialuni-dating-view-uni/src/views/SocialuniDatingView.vue";
@Options({
  components: {SocialuniDatingView}
})
export default class ChatPage extends Vue {

}
</script>
