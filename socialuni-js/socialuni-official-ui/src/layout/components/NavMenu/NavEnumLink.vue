<template>
  <component :is="type" v-bind="linkProps(to)">
    <slot />
  </component>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-facing-decorator'

@toNative
@Component({})
export default class NavEnumLink extends Vue {
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
</script>
