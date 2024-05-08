<template>
  <component :is="type" v-bind="linkProps(to)">
    <slot />
  </component>
</template>

<script lang="ts">
import { Component, Prop, Vue , toNative} from 'vue-facing-decorator'
import CommonUtil from "qing-util/src/util/CommonUtil";

@toNative
@Component
export default class QEnumLink extends Vue {
  @Prop() to: string

  get isExternal() {
    return CommonUtil.isExternal(this.to)
  }

  get type() {
    if (this.isExternal) {
      return 'a'
    }
    return 'router-link'
  }

  linkProps(to) {
    if (this.isExternal) {
      return {
        href: to,
        target: '_parent'
      }
    }
    return {
      to: to
    }
  }
}
</script>
