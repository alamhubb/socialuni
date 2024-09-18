<template>
  <div class="row-between-center index-xs">
    <div class="row-col-center">
      <slot name="actionLeft">

      </slot>
    </div>
    <div class="row-col-center">
      <slot name="actionRight">
        <q-button
          size="small"
          v-show="!hideCancel"
          @click="cancel">{{ cancelText }}
        </q-button>
        <slot></slot>
        <!--        有防抖所以无法连续触发-->
        <q-button
            primary
          size="small"
          v-show="!hideConfirm"
          :click="confirm"
          @click="emitConfirm"
          :no-debounce="noDebounce"
          :show-loading="showLoading"
          @click-after="confirmAfter"
          :disabled="disabled"
          class="ml-sm">
          {{ confirmText }}
        </q-button
            primary>
      </slot>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue , toNative} from 'vue-facing-decorator'
import QButton from "qingjs-ui/src/components/QButton.vue";


@Component({
  components: { QButton }
})
class QPageFooter extends Vue {
  @Prop({ default: false, type: Boolean }) hideConfirm
  @Prop({ default: false, type: Boolean }) hideCancel
  @Prop({ default: '确 定' }) confirmText: string
  @Prop({ default: '取 消' }) cancelText: string
  @Prop({ default: false, type: Boolean }) disabled: boolean
  @Prop({ default: false, type: Boolean }) cancelDisabled: boolean
  @Prop({ default: false, type: Boolean }) noDebounce: boolean
  @Prop({ default: false, type: Boolean }) showLoading: boolean
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
export default toNative(QPageFooter)
</script>
