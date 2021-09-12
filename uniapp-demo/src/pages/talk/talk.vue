<template>
  <social-talk-page></social-talk-page>
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
