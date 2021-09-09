<template>
  <social-talk-page class="flex-1"></social-talk-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTalkPage from 'socialuni/components/SocialPages/SocialTalkPage.vue'

@Component({
  components: { SocialTalkPage }
})
export default class TalkPage extends Vue {
}
</script>
