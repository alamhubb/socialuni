<template>
  <button
    class="px-smm py-sm bg-click font-14" style="line-height: 1" :class="buttonClass"
    @click.stop="clickHandler"
    :disabled="disabled"
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
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import ComUtil from '../utils/ComUtil'

@Component
export default class QButton extends Vue {
  //颜色
  @Prop() color: string
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
  }) df: boolean
  @Prop({
    default: false,
    type: Boolean
  }) sm: boolean
  @Prop({
    default: false,
    type: Boolean
  }) xs: boolean

  // 节流，一定时间内只能触发一次
  @Prop({
    default: 1000,
    type: [String, Number]
  }) throttleTime: string | number

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


  get buttonClass () {
    return [
      //颜色
      'bg-' + (this.theme ? 'theme' : this.primary ? 'primary' : this.error ? 'error' : this.warn ? 'warn' : this.success ? 'success' : this.info ? 'info' : this.color || 'white'),
      //类型
      this.dark ? 'dark' : this.light ? 'light' : this.plain ? 'plain' : this.colorType || 'dark',
      //大小
      this.df ? 'df' : this.md ? 'md' : this.sm ? 'sm' : this.xs ? 'xs' : this.size || 'df',
      this.disabled ? 'disabled' : ''
    ]
  }

  clickHandler = ComUtil.throttle(this.click, 1000)

  @Emit()
  click (e) {
    return e
  }

  // 下面为对接uniapp官方按钮开放能力事件回调的对接
  @Emit()
  getphonenumber (res) {
    return res
  }

  @Emit()
  getuserinfo (res) {
    return res
  }

  @Emit('error')
  errorHandler (res) {
    return res
  }

  @Emit()
  opensetting (res) {
    return res
  }

  @Emit()
  launchapp (res) {
    return res
  }
}
</script>
