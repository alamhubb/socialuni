<template>
  <div class="row-all-center" :class="[themeClass]">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-facing-decorator'
import QIcon from "./QIcon/QIcon.vue";

@Component({
  components: {
    QIcon
  }
})
export default class QTag extends Vue {
  @Prop({default: false, type: Boolean}) border: boolean
  @Prop({default: false, type: Boolean}) round: boolean
  @Prop({default: '', type: Boolean}) type: string
  @Prop({default: 'mn', type: String}) size: string

  get themeClass() {
    let className = 'q-tag'
    if (this.type) {
      className += ('-' + this.type)
    }
    if (this.border) {
      className += ('-bd')
    }
    if (this.size) {
      className += (' box-' + this.size)
    }
    if (this.round) {
      className += (' bd-round')
    }
    return className
  }
}
</script>
