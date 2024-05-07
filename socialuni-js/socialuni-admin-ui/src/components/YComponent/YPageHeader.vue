<template>
  <div class="y-page-header h50 px row-between-center bb flex-none">
    <div class="font-16 color-special font-bold">
      <div class="row-col-center">
        <slot>
          <slot name="left">
            <slot name="titlePre"></slot>
            <slot name="title">
<!--              <div v-if="showPiece" class="w3 h16 bg-blue mr-sm"></div>-->
<!--              <div class="y-piece mb-nn"></div>-->
              <div class="font-16 font-bold">{{ title }}</div>
            </slot>
          </slot>
        </slot>
      </div>
    </div>
    <div class="row-col-center">
      <slot name="right">

      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component
export default class YPageHeader extends Vue {
  @Prop() title: string
  @Prop({default: false, type: Boolean}) showPiece: boolean
}

</script>
