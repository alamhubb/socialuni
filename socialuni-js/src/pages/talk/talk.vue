<template>
  <talk-list></talk-list>
</template>

<script lang="ts">
import TalkList from '@/pages/talk/TalkList.vue'
import { Component, Vue } from 'vue-property-decorator'

@Component({
  components: { TalkList }
})
export default class TalkPage extends Vue {
}
</script>
