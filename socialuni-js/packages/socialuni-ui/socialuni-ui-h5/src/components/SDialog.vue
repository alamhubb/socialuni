<!--
弹框的开启需要在父组件为弹框组件设置ref，通过 this.$refs['dialog'].dialogVisible = true,或者使用open，close方法 使弹框显示

  弹框内容body有两种展现方式，单行、双行 分别对应两个slot，按需使用即可
// 更多参数和事件参考el-dialog https://element.eleme.cn/#/zh-CN/component/dialog
  width：弹框宽度，支持%、如需要通过prop修改
  top：弹框与页面顶部的距离，默认 15vh，如需要通过prop修改
  closeOnPressEscape：是否可以通过按下 ESC 关闭 Dialog，默认false，如需要通过prop修改
  closeOnClickModal：是否可以通过点击 modal 关闭 Dialog，默认false，如需要通过prop修改
-->
<template>
  <!-- v-bind="$attrs"  将外部参数绑定到指定内部组件，详见： https://cn.vuejs.org/v2/api/#vm-attrs-->
  <!-- v-on="$listeners"  将外部事件绑定到指定内部组件，详见： https://cn.vuejs.org/v2/api/#vm-listeners-->
  <el-dialog
      :title="title"
      v-bind="$attrs"
      v-model="dialogVisible"
      :width="width"
      append-to-body
      @open="emitOpen"
      @close="dialogClose"
      :top='top'>

    <template #title>
      <slot name="title"></slot>
    </template>

    <!--  自定义区域    -->
    <div :style="{height: dialogBodyHeight}">123
      <s-scrollbar class="h100p">
        <div>
          <slot></slot>
        </div>
      </s-scrollbar>
    </div>

    <div
        v-if="!noShowFooter"
        class="row-end-center shadow-top index-xs px py-10 mt-mn">
      <!-- dialog底部自定义弹框区域 -->
      <slot name="dialogFooter"></slot>
      <!--   不显示确定的时候，取消按钮改名为关闭-->
      <s-page-footer
          v-if="!hideBtn && dialogVisible"
          :hide-cancel="hideCancel"
          :hide-confirm="hideConfirm"
          :confirm-text="confirmText"
          :show-loading="configLoad"
          :disabled="disabled"
          :no-debounce="noDebounce"
          :cancel-text="hideConfirm?'关 闭' :cancelText"
          @cancel="dialogCancel"
          :confirm="confirmMethod()"
          @confirm="dialogConfirm"
          @confirm-after="confirmAfter"
      >
      </s-page-footer>
    </div>
  </el-dialog>
</template>
<script lang="ts">
import {Options, Emit, Prop, Vue, Watch} from 'vue-property-decorator'
import SPageFooter from "./SPageFooter.vue";
import SScrollbar from "./SScrollbar.vue";

@Options({
  components: {SScrollbar, SPageFooter}
})
export default class SDialog extends Vue {
  // 作用，下次在进入页面时，重置按钮焦点使用，否则会存在那种再次进入按钮已经获取焦点的问题
  reloadPageFooterFlag = true
  dialogVisible = false
  // 用来判断，右上角关闭的时候，是否触发cancel事件
  isBtnClose = false

  @Prop() title: string
  @Prop({
    type: Boolean,
    default: false
  }) visible: boolean


  @Prop({
    type: Boolean,
    default: false
  }) noDebounce: boolean

  // dialog宽度
  @Prop({
    default: '30%'
  }) width: string

  @Prop({
    default: '12vh'
  }) top: string

  // 设置弹框body部分的高度，默认500px
  @Prop({
    default: '350px'
  }) dialogBodyHeight: string


  // 默认按钮是否需要
  @Prop({
    type: Boolean,
    default: false
  }) hideBtn: boolean


  // 默认按钮是否需要
  @Prop({
    type: Boolean,
    default: false
  }) hideConfirm: boolean
  // 默认按钮是否需要
  @Prop({
    type: Boolean,
    default: false
  }) noShowFooter: boolean
  @Prop({
    type: Boolean,
    default: false
  }) hideCancel: boolean


  @Prop({
    default: '确 定'
  }) confirmText: string

  @Prop({
    default: '取 消'
  }) cancelText: string


  @Prop({
    type: Boolean,
    default: false
  }) disabled: boolean

  // 确定按钮的loading状态 ，已经支持了自动设置按钮loading
  @Prop({
    type: Boolean,
    default: false
  }) configLoad: boolean

  // 确定按钮的loading状态
  @Prop() confirm: any

  @Prop({
    type: Boolean,
    default: true
  }) autoClose: boolean

  // 关闭按钮，默认自动关闭弹窗，默认自动关闭弹窗，可以通过这个属性，设置手动关闭弹窗
  @Prop({
    type: Boolean,
    default: false
  }) customCancelClose: boolean


  @Watch('visible')
  visibleWatch() {
    if (this.visible) {
      this.open()
    } else {
      this.close()
    }
  }


  // 提供给外部使用的方法
  open() {
    console.log('触发了')
    // 重置按钮状态
    // this.reloadPageFooterDiv()
    this.clearValidate()
    /* this.$nextTick(() => {
      this.dialogVisible = true
    })*/
    this.dialogVisible = true
    console.log(this.dialogVisible)
    this.modifyVisible()
  }


  clearValidate() {
    this.$nextTick(() => {
      if (this.$slots.default && this.$slots.default[0] && this.$slots.default[0].componentInstance && this.$slots.default[0].componentInstance.clearValidate) {
        // @ts-ignore
        this.$slots.default[0].componentInstance.clearValidate()
      }
    })
  }


  close() {
    this.isBtnClose = true
    this.dialogVisible = false
    this.modifyVisible()
  }


  modifyVisible() {
    this.$emit('update:visible', this.dialogVisible)
  }


  confirmAfter() {
    this.close()
  }

  // 关闭弹窗并且执行取消
  // dialog取消按钮事件
  dialogCancel() {
    if (!this.customCancelClose) {
      this.close()
    }
    this.$emit('cancel')
  }


  confirmMethod() {
    if (this.confirm) {
      return async () => {
        try {
          // 支持自动校验form
          // @ts-ignore
          if (this.$slots.default && this.$slots.default[0] && this.$slots.default[0].componentInstance && this.$slots.default[0].componentInstance.validate) {
            // @ts-ignore
            await this.$slots.default[0].componentInstance.validate()
          }
          if (typeof this.confirm === 'function') {
            await this.confirm()
          } else {
            // 获取方法和参数列表
            const clickMethodAndArgsAry = this.confirm
            // 获取方法
            const clickMethod = clickMethodAndArgsAry[0]
            // 判断您是否包含参数
            if (clickMethodAndArgsAry.length) {
              const args = clickMethodAndArgsAry.slice(1, clickMethodAndArgsAry.length)
              await clickMethod(...args)
            } else {
              await clickMethod()
            }
          }
        } catch (e) {
          throw new Error(e)
        }
      }
    }
    return null
  }


  // dialog 确定事件
  dialogConfirm() {
    this.$emit('confirm')
    // if (this.autoClose) {
    //   this.close()
    // }
  }


  // 此方法仅内部使用,仅为右上角关闭弹窗时调用，关闭弹窗时触发， 需要判断你是不是自定义按钮的触发，还是右上角关闭的触发，右上角关闭触发则触发取消事件
  dialogClose() {
    if (this.isBtnClose) {
      // 如果为自定义按钮触发，因为之前已经触发过关闭了，所以无需执行逻辑，重置flag即可
      this.isBtnClose = false
    } else {
      // 不为自定义关闭，而是右上角关闭，则触发cancel
      this.dialogCancel()
    }
    this.$emit('close')
  }

  // 刷新组件，清空之前的操作
  reloadPageFooterDiv() {
    this.reloadPageFooterFlag = false
    this.$nextTick(() => {
      this.reloadPageFooterFlag = true
    })
  }


  emitOpen() {
    this.$emit('open')
  }
}


</script>
