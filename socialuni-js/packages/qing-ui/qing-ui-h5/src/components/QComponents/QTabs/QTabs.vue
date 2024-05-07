<template>
  <div class="flex-col position-relative">
    <div :class="[isXDirection?'flex-row':'flex-col']" class="overflow-auto">
      <div
        v-for="(tab,index) in tabs"
        ref="tabs"
        :key="index"
        class="use-click flex-1"
        @click="change(tab,index)"
      >
        <slot :tab="tab" :index="index">
          <div
            class="col-row-center py-sm px-sm font-14"
            :class="[(value?tab[value]:index)===model?('color-blue '+activeClass):'color-gray']"> {{ tab[label || value] }}
          </div>
        </slot>
      </div>
    </div>
    <!--    <div class="position-absolute bg-blue" :style="[tabSlideStyle]" />-->
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, toNative, Vue, Watch} from 'vue-facing-decorator'
import {TabDirection} from "./TabDirection";

// 组件需求，支持x轴，y轴滚动，x轴和y轴可自定义设置tab高度和宽度
@toNative
@Component({})
export default class QTabs extends Vue {
  $refs: {
    tabs: HTMLDivElement[];
  }

  @Prop({ default: TabDirection.row }) readonly direction: TabDirection

  @Prop({ default: 100 }) readonly preShow: number

  @Prop({ default: [] }) readonly tabs: any[]

  /**
   * 当前选中的tab的索引
   */
  @Model model: any
  @Prop({ default: 'value' }) value: string
  @Prop() label: string
  @Prop() activeClass: string

  @Prop({ default: '10' }) readonly barWidth: string
  @Prop({ default: '10' }) readonly barHeight: string

  /**
   * 记录左侧每个索引对应的滚动位置
   */
  tabItemTranslates: number[] = [0]

  // 判断是否已加载过
  firstLoadAfter = false

  /**
   * 提前量，提前多少高度px显示到下一个
   */

  @Emit('update:modelValue')
  change(tab: any, index: number) {
    if (this.value) {
      return tab[this.value]
    }
    return index
  }

  // 渲染完毕后计算高度
  mounted() {
    this.init()
  }

  // 设置一个init方法，方便多处调用
  init() {
    // 等待元素渲染
    this.getTabRect()
  }

  @Watch('tabs')
  watchTabsChange() {
    this.init()
  }

  // 查询tab的布局信息
  getTabRect() {
    this.$nextTick(() => {
      // 第一个的位置等于第一个距离左侧的位置 + 第一个的宽度/2 - bar的宽度/2
      // 都要加上第一个距离左侧的位置
      const res = this.$refs.tabs
      this.tabItemTranslates = []
      // 获取padding
      if (res && res.length) {
        res.forEach(item => {
          let translate
          // 如果不为横轴
          if (this.isXDirection) {
            // 横轴就是横向定位
            translate = item.offsetLeft + item.clientWidth / 2 - (Number(this.barWidth) / 2)
          } else {
            // 纵轴高度定位
            translate = item.offsetTop + item.clientHeight / 2 - (Number(this.barHeight) / 2)
          }
          this.tabItemTranslates.push(translate)
        })
      }
    })
  }

  // 如果水平方向
  get isXDirection(): boolean {
    return this.direction === TabDirection.row
  }

  // 滑块样式
  get tabSlideStyle() {
    const styleObj = {
      // 首次不开启动画
      // 'transition-duration': this.firstLoadAfter ? '3s' : '0',
      'transition-duration': '.3s',
      width: this.barWidth + 'px',
      height: this.barHeight + 'px',

      transform: null,
      bottom: null,
      right: null
    }
    if (this.isXDirection) {
      styleObj.transform = 'translateX(' + this.tabItemTranslates[this.value] + 'px)'
      styleObj.bottom = 0
    } else {
      styleObj.transform = 'translateY(' + this.tabItemTranslates[this.value] + 'px)'
      styleObj.right = 0
    }
    return styleObj
  }
}
</script>
