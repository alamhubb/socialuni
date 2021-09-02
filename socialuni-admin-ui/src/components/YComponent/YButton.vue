<template>
  <el-button v-bind="$attrs" v-on="$listeners" @click="btnClick" :disabled="btnDisabled" :loading="btnDisabled">
    <slot></slot>
  </el-button>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'

@Component
export default class YButton extends Vue {
  @Prop({ default: false, type: Boolean }) disabled: boolean
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

  async btnClick() {
    if (this.btnEnable) {
      this.btnEnable = false
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
        this.btnEnable = true
      }
    }
  }

  @Emit()
  clickAfter() {
    return
  }
}

</script>
