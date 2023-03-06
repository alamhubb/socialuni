<template>
  <button
      class="row-all-center line-h1"
      :class="[buttonClass,addClass]"
      @click.stop="clickHandler"
      :disabled="btnDisabled"
      :loading="loading"
      :form-type="formType"
      :open-type="openType"
      :hover-class="hoverClass"
      :hover-start-time="Number(hoverStartTime)"
      :hover-stay-time="Number(hoverStayTime)"
      :app-parameter="appParameter"
      :hover-stop-propagation="hoverStopPropagation"
      :lang="lang"
      :session-from="sessionFrom"
      :send-message-title="sendMessageTitle"
      :send-message-path="sendMessagePath"
      :send-message-img="sendMessageImg"
      :show-message-card="showMessageCard"
      @getphonenumber="getphonenumber"
      @getuserinfo="getuserinfo"
      @error="errorHandler"
      @opensetting="opensetting"
      @launchapp="launchapp"
  >
    <slot></slot>
  </button>
</template>

<script lang="ts">
import {Options, Emit, Prop, Vue} from 'vue-property-decorator'
import CommonUtil from "socialuni-sdk/src/utils/CommonUtil";

@Options({})
export default class QButton extends Vue {

  @Prop() addClass: string
  @Prop({
    default: false,
    type: Boolean
  }) text: boolean
  @Prop({
    default: false,
    type: Boolean
  }) round: boolean
  @Prop({
    default: false,
    type: Boolean
  }) theme: boolean
  @Prop({
    default: false,
    type: Boolean
  }) primary: boolean
  @Prop({
    default: false,
    type: Boolean
  }) error: boolean
  @Prop({
    default: false,
    type: Boolean
  }) warn: boolean
  @Prop({
    default: false,
    type: Boolean
  }) success: boolean
  @Prop({
    default: false,
    type: Boolean
  }) info: boolean

  //类型
  @Prop() colorType: string
  @Prop({
    default: false,
    type: Boolean
  }) dark: boolean
  @Prop({
    default: false,
    type: Boolean
  }) light: boolean
  @Prop({
    default: false,
    type: Boolean
  }) plain: boolean

  //大小
  @Prop() size: string
  @Prop({
    default: false,
    type: Boolean
  }) md: boolean
  @Prop({
    default: false,
    type: Boolean
  }) lg: boolean
  @Prop({
    default: false,
    type: Boolean
  }) df: boolean
  @Prop({
    default: false,
    type: Boolean
  }) sm: boolean
  @Prop({
    default: false,
    type: Boolean
  }) xs: boolean

  // 防抖，一定时间内只能触发一次
  @Prop({
    default: 1000,
    type: [String, Number]
  }) debounceTime: string | number

  //uni参数
  //是否禁用
  @Prop({
    default: false,
    type: Boolean
  }) disabled: boolean
  //名称前是否带 loading 图标
  @Prop({
    default: false,
    type: Boolean
  }) loading: boolean

  // 用于 <form> 组件，点击分别会触发 <form> 组件的 submit/reset 事件
  @Prop({
    default: '',
    type: String
  }) formType: string

  // 开放能力
  @Prop({
    default: '',
    type: String
  }) openType: string

  // 指定按钮按下去的样式类。当 hover-class="none" 时，没有点击态效果
  @Prop({
    default: '',
    type: String
  }) hoverClass: string

  // 按住后多久出现点击态，单位毫秒
  @Prop({
    default: 20,
    type: [String, Number]
  }) hoverStartTime: string | number

  // 手指松开后点击态保留时间，单位毫秒
  @Prop({
    default: 70,
    type: [String, Number]
  }) hoverStayTime: string | number

  // 打开 APP 时，向 APP 传递的参数，open-type=launchApp时有效
  @Prop({
    default: '',
    type: String
  }) appParameter: string

  // 指定是否阻止本节点的祖先节点出现点击态
  @Prop({
    default: false,
    type: Boolean
  }) hoverStopPropagation: boolean

  // 指定返回用户信息的语言，zh_CN 简体中文，zh_TW 繁体中文，en 英文。
  @Prop({
    default: 'en',
    type: String
  }) lang: string

  // 会话来源，open-type="contact"时有效
  @Prop({
    default: '',
    type: String
  }) sessionFrom: string

  // 会话内消息卡片标题，open-type="contact"时有效
  @Prop({
    default: '',
    type: String
  }) sendMessageTitle: string

  // 会话内消息卡片点击跳转小程序路径，open-type="contact"时有效
  @Prop({
    default: '',
    type: String
  }) sendMessagePath: string

  // 会话内消息卡片图片，open-type="contact"时有效
  @Prop({
    default: '',
    type: String
  }) sendMessageImg: string

  // 是否显示会话内消息卡片，设置此参数为 true，用户进入客服会话会在右下角显示"可能要发送的小程序"提示，用户点击后可以快速发送小程序消息，open-type="contact"时有效
  @Prop({
    default: '',
    type: String
  }) showMessageCard: string

  @Prop({
    default: false,
    type: Boolean
  }) noDebounce: boolean
//颜色, 要支持用户自定义色值，theme
  @Prop({
    default: 'theme',
    type: String
  }) color: string

  get curTheme() {
    if (this.theme) return 'theme'
    if (this.primary) return 'primary'
    if (this.error) return 'error'
    if (this.warn) return 'warn'
    if (this.success) return 'success'
    if (this.info) return 'info'
    return this.color
  }

  get curSize() {
    if (this.xs) return 'xs'
    if (this.sm) return 'sm'
    if (this.df) return 'df'
    if (this.md) return 'md'
    if (this.lg) return 'lg'
    return 'df'
  }

  get buttonClass() {
    let buttonClass = `${this.btnDisabled ? 'bg-disabled' : 'use-click'} `
    if (this.text) {
      buttonClass += `bd-none bg-theme-light color-${this.curTheme}`
    } else {
      buttonClass += `chunk${this.curTheme ? ('-' + this.curTheme) : ''}${this.light ? ('_light') : ''}${this.plain ? ('_plain') : ''} q-box-${this.curSize} font-${this.curSize}`
      if (this.round) {
        buttonClass += ' bd-round'
      }
    }
    return buttonClass
  }


  // 下面为对接uniapp官方按钮开放能力事件回调的对接
  @Emit()
  getphonenumber(res) {
    return res
  }

  @Emit()
  getuserinfo(res) {
    return res
  }

  @Emit('error')
  errorHandler(res) {
    return res
  }

  @Emit()
  opensetting(res) {
    return res
  }

  @Emit()
  launchapp(res) {
    return res
  }


  @Prop({
    default: true,
    type: Boolean
  }) showLoading: boolean
  @Prop({
    default: () => {
      return null
    },
    type: Function
  }) click: () => Promise<void> | Array<() => Promise<void> | any>

  btnEnable = true

  get btnDisabled() {
    return this.disabled || !this.btnEnable
  }

  get clickHandler() {
    return CommonUtil.debounce(this.btnClick, this.noDebounce ? 0 : Number(this.debounceTime))
  }

  async btnClick() {
    if (this.btnEnable) {
      this.btnEnable = false
      this.clickEmit()
      try {
        if (this.click) {
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
        }
      } finally {
        if (this.click && !this.noDebounce) {
          await CommonUtil.setTimeout(Number(this.debounceTime))
        }
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
<!--
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-df bd-radius bg-click"
     :class="'chunk-'+item">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-df bd-radius bg-click"
     :class="'chunk-'+item+'_light'">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-df bd-radius bg-click"
     :class="'chunk-'+item+'_plain'">
  哈哈
</div>
</div>

<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-sm bd-radius bg-click"
     :class="'chunk-'+item">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-sm bd-radius bg-click"
     :class="'chunk-'+item+'_light'">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-sm bd-radius bg-click"
     :class="'chunk-'+item+'_plain'">
  哈哈
</div>
</div>

<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-mn bd-radius bg-click"
     :class="'chunk-'+item">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-mn bd-radius bg-click"
     :class="'chunk-'+item+'_light'">
  哈哈
</div>
</div>
<div class="flex-row">
<div v-for="item in ['primary','warn','success','error']" class="mg-sm q-box-mn bd-radius bg-click"
     :class="'chunk-'+item+'_plain'">
  哈哈
</div>
</div>-->
