<template>
  <div class="row-between-center w100p shadow-top">
    <div class="flex-row">
      <slot name="actionLeft">

      </slot>
    </div>
    <div class="flex-row">
      <slot name="actionRight">
        <el-button size="mini" v-show="!hideCancel" @click="cancel">{{ cancelText }}</el-button>
        <slot></slot>
        <!--        有防抖所以无法连续触发-->
        <y-button
          size="mini"
          v-show="!hideConfirm"
          :click="confirm"
          @click="emitConfirm"
          @click-after="confirmAfter"
          :disabled="disabled"
          class="yy-btn">
          {{ confirmText }}
        </y-button>
      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import YButton from '@/components/YComponent/YButton.vue'

@Component({
  components: { YButton }
})
export default class YPageFooter extends Vue {
  @Prop({ default: false, type: Boolean }) hideConfirm
  @Prop({ default: false, type: Boolean }) hideCancel
  @Prop({ default: '确 定' }) confirmText: string
  @Prop({ default: '取 消' }) cancelText: string
  @Prop({ default: false, type: Boolean }) disabled: boolean
  @Prop({ default: false, type: Boolean }) cancelDisabled: boolean
  @Prop({
    default: null,
    type: Function
  }) confirm: () => Promise<void> | Array<() => Promise<void> | any>

  @Emit('cancel')
  private cancel() {
    return
  }

  @Emit('confirm')
  emitConfirm() {
    return
  }

  @Emit()
  private confirmAfter() {
    return
  }
}

</script>
