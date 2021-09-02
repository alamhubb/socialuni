<template>
  <el-dialog
    append-to-body
    :visible.sync="showDialog"
    :close-on-click-modal="false"
    :title="title"
    v-bind="$attrs"
    @close="dialogCloseClick"
  >
    <slot />
    <div>
      <slot name="action">
        <!--   不显示确定的时候，取消按钮改名为关闭-->
        <y-page-footer
          v-if="reloadPageFooterFlag"
          class="pt"
          :hide-cancel="hideCancel"
          :hide-confirm="hideConfirm"
          :confirm-title="confirmTitle"
          :cancel-title="hideConfirm?'关闭' :cancelTitle"
          :confirm="confirm"
          @cancel="cancelBtnClick"
          @confirm-after="close"
        />
      </slot>
    </div>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import YPageFooter from '@/components/YComponent/YPageFooter.vue'

@Component({
  components: { YPageFooter }
})
export default class YDialog extends Vue {
  @Prop({ default: '', type: String }) title
  @Prop({ default: false, type: Boolean }) hideConfirm
  @Prop({ default: false, type: Boolean }) hideCancel
  @Prop() cancelTitle
  @Prop() confirmTitle
  @Prop({
    default: () => {
      return
    },
    type: Function
  }) confirm: () => Promise<void> | Array<() => Promise<void> | any>

  reloadPageFooterFlag = true

  showDialog = false

  isCustomClose = false

  open() {
    // 重置按钮状态
    this.reloadPageFooterDiv()
    this.showDialog = true
  }

  close() {
    this.isCustomClose = true
    this.showDialog = false
  }

  // 刷新组件，清空之前的操作
  reloadPageFooterDiv() {
    this.reloadPageFooterFlag = false
    this.$nextTick(() => {
      this.reloadPageFooterFlag = true
    })
  }

  // 关闭弹窗时触发， 需要判断你是不是自定义按钮的触发，还是右上角关闭的触发，右上角关闭触发则触发取消事件
  private dialogCloseClick() {
    if (!this.isCustomClose) {
      this.cancel()
    } else {
      // 如果为自定义按钮触发，因为之前已经触发过关闭了，所以无需执行逻辑，重置flag即可
      this.isCustomClose = false
    }
  }

  // 关闭弹窗并且执行取消
  private cancelBtnClick() {
    this.close()
    this.cancel()
  }

  // 确定不能执行close， 取消不能执行两次，close一次

  @Emit()
  private cancel() {
    return
  }
}
</script>
