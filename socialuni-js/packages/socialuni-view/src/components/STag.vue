<template>
  <div class="row-all-center" :class="[themeClass]">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UserUtil from "socialuni-sdk/src/utils/UserUtil";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";

@Options({
  components: {
    QIcon
  }
})
export default class SocialuniTag extends Vue {
  @Prop({default: false, type: Boolean}) border: boolean
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
    return className
  }
}
</script>
