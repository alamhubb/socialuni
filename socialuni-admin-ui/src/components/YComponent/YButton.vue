<template>
  <el-button
    v-bind="$attrs"
    @click="clickHandler"
    :disabled="btnDisabled"
    :loading="!btnEnable && showLoading && btnDisabled">
    <slot></slot>
  </el-button>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue} from 'vue-property-decorator'
import CommonUtil from "@/utils/CommonUtil";

@Component
export default class YButton extends Vue {
  @Prop({default: false, type: Boolean}) disabled: boolean
  @Prop({default: true, type: Boolean}) showLoading: boolean
  @Prop({
    default: () => {
      return
    },
    type: Function
  }) click: () => Promise<void> | Array<() => Promise<void> | any>

  btnEnable = true

  get btnDisabled() {
    return this.disabled || !this.btnEnable
  }

  clickHandler = CommonUtil.debounce(this.btnClick, 1000)

  async btnClick() {
    if (this.btnEnable) {
      this.btnEnable = false
      this.clickEmit()
      try {
        if (typeof this.click === 'function') {
          await this.click()
        } else {
          // 获取方法和参数列表
          const clickMethodAndArgsAry = this.click as Array<() => Promise<void> | any>
          // 获取方法
          const clickMethod = clickMethodAndArgsAry[0] as (values?: any[]) => Promise<void>
          // 判断您是否包含参数
          if (clickMethodAndArgsAry.length) {
            const args: any[] = clickMethodAndArgsAry.slice(1, clickMethodAndArgsAry.length)
            await clickMethod(...args)
          } else {
            await clickMethod()
          }
        }
        // 只有方法正常执行完毕才会触发click
        this.clickAfter()
      } finally {
        await CommonUtil.setTimeout()
        this.btnEnable = true
      }
    }
  }

  //需要有两个返回，第一个结果
  //第二个，解除禁用，每次点击都延迟一秒解禁可以。 接口返回后，延迟一秒，解除禁用


  @Emit('click')
  clickEmit() {
    return null
  }


  @Emit()
  clickAfter() {
    return null
  }
}

</script>
