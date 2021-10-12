<template>
  <social-talk-page ref="socialTalk"></social-talk-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTalkPage from 'socialuni/components/SocialPages/SocialTalkPage.vue'

@Component({
  components: { SocialTalkPage }
})
export default class TalkPage extends Vue {
  $refs: {
    socialTalk: SocialTalkPage
  }

  onPageScroll (e) {
    this.$refs.socialTalk.socialTalkScroll(e)
  }
}
</script>
