<template>
  <component :is="type" v-bind="linkProps(to)">
    <slot />
  </component>
</template>

<script lang="ts">
import { Component, Prop, Vue , toNative} from 'vue-facing-decorator'


@Component({})
class NavEnumLink extends Vue {
  @Prop() to: string

  get isExternal() {
    return /^(https?:|mailto:|tel:)/.test(this.to)
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
export default toNative(NavEnumLink)
</script>
