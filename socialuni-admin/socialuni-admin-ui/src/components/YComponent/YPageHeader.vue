<template>
  <div class="row-between-center px py-smm bb-divider">
    <div>
      <slot name="left">
        <slot name="title">
          <div class="row-col-center">
            <div class="w3 h16 bg-blue mr-sm"></div>
            <div class="font-18 font-bold">{{ title }}</div>
          </div>
        </slot>
      </slot>
    </div>
    <div>
      <slot name="right">

      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class YPageHeader extends Vue {
  @Prop() title: string
}

</script>
