<template>
  <div class="row-between-center pd bt-grey">
    <div>
      <slot name="left">

      </slot>
    </div>
    <div>
      <slot name="right">
        <div class="flex-row">
          <el-button v-show="!hideCancel" @click="cancel">
            {{ cancelTitle }}
          </el-button>
          <slot name="rightCenter"></slot>
          <y-button
            v-show="!hideConfirm"
            :click="confirm"
            type="danger"
            :disabled="disabled"
            @click-after="confirmAfter">
            {{ confirmTitle }}
          </y-button>
        </div>
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
  @Prop({ default: '确定' }) confirmTitle: boolean
  @Prop({ default: '取消' }) cancelTitle: boolean
  @Prop({ default: false, type: Boolean }) disabled: boolean
  @Prop({
    default: () => {
      return
    },
    type: Function
  }) confirm: () => Promise<void> | Array<() => Promise<void> | any>

  @Emit()
  private cancel() {
    return
  }

  @Emit()
  private confirmAfter() {
    return
  }
}

</script>
