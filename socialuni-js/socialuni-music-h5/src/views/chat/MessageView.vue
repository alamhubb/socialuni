<template>
  <div class="flex-col h100p overflow-hidden pd bg-white">
    <div class="flex-row overflow-hidden flex-none">
      <div v-for="i in 20" class="flex-1 w200 h30" :class="[active===i?'bg-blue_light bb-2 bb-blue':'bg-default']" @click="active=i">
        {{ i }}
      </div>
    </div>
    <div class="flex-1 overflow-hidden bg-green_light pd">
    {{ active}}
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';

@Component({
  components: {}
})
export default class MessageView extends Vue {

  active = 1
}
</script>
