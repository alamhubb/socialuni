<template>
  <el-dialog
    auto-close
    append-to-body
    :visible.sync="showDialog"
    :close-on-click-modal="false"
    :title="title"
    v-bind="$attrs"
    class="neibuDialog"
    @close="dialogClose"
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
          :confirm-text="confirmTitle"
          :cancel-text="hideConfirm?'关闭' :cancelTitle"
          :confirm="confirm"
          @cancel="dialogCancel"
          @confirm="dialogConfirm"
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
    }
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
  private reloadPageFooterDiv() {
    this.reloadPageFooterFlag = false
    this.$nextTick(() => {
      this.reloadPageFooterFlag = true
    })
  }

  // 关闭弹窗时触发， 需要判断你是不是自定义按钮的触发，还是右上角关闭的触发，右上角关闭触发则触发取消事件
  private dialogClose() {
    if (this.isCustomClose) {
      // 如果为自定义按钮触发，因为之前已经触发过关闭了，所以无需执行逻辑，重置flag即可
      this.isCustomClose = false
    } else {
      // 不为自定义关闭，而是右上角关闭，则触发cancel
      this.dialogCancel()
    }
  }

  // 关闭弹窗并且执行取消

  // 确定不能执行close， 取消不能执行两次，close一次

  @Emit('cancel')
  private dialogCancel() {
    this.close()
    return
  }

  @Emit('confirm')
  private dialogConfirm() {
    return
  }
}
</script>
<style>
.neibuDialog .el-dialog__header {
  border-bottom: 1px #E8E8E8 solid;
}

.neibuDialog .el-dialog__title {
  font-size: 18px;
  font-weight: bold;
  color: #1F2329;
}

.neibuDialog .el-dialog__body {
  padding: 20px;
}

.neibuDialog .el-dialog__body .is-required .el-form-item__label::before {
  content: '';
}

.neibuDialog .el-dialog__body .is-required .el-form-item__label:after {
  content: '*';
  color: #F56C6C;
  margin-left: 4px;
}

.neibuDialog .el-dialog__body .el-form-item {
  margin-bottom: 10px;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__label {
  display: block;
  width: 100% !important;
  text-align: left;
  height: 28px;
  font-size: 14px;
  color: #333333;
  font-weight: 600;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content {
  margin-left: 0 !important;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-input-number {
  width: 100%;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-input-number ..el-input__inner {
  text-align: left;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-input {
  height: 32px;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-input .el-input__inner {
  height: 32px;
  line-height: 32px;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-select {
  width: 100%;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-select .el-input__suffix {
  height: 40px;
  line-height: 40px;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-cascader {
  width: 100%;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-col-2 {
  text-align: center;
}

.neibuDialog .el-dialog__body .el-form-item .el-form-item__content .el-textarea {
  margin-top: 5px;
}

.neibuDialog .el-dialog__body .el-form-item--small .el-form-item__content .el-select .el-input__suffix {
  height: 32px;
  line-height: 32px;
}

.neibuDialog .el-dialog__footer .el-button {
  padding: 9px 15px;
  font-size: 12px;
}
</style>
