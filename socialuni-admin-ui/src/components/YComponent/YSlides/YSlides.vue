<template>
  <div ref="rightBox" class="overflow-auto" @scroll="rightBoxScroll">
    <div
      v-for="(item,index) in data"
      ref="slides"
      :key="index"
      :style="{'min-height': index === data.length - 1 ? rightLastHeightPx : ''}"
    >
      <slot name="default" :item="item" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'

/**
 * 获取每一块的高度
 */

@Component
export default class YSlides extends Vue {
  $refs: {
    slides: HTMLDivElement[];
    rightBox: HTMLDivElement;
  }
  /**
   * 展示的数据
   */
  @Prop() readonly data: any []
  // 默认index，不传value默认索引
  @Prop() readonly value: string

  @Model('change') readonly model: any

  /**
   * 提前量，提前多少高度px显示到下一个
   */
  @Prop({ default: 100 }) readonly preShow: number

  // 最后一个元素占满屏幕高度，如果不给额外高度，无法滚动到最后一个，因为是靠上边界决定左侧菜单的
  private get rightLastHeightPx() {
    return this.componentHeight + 'px'
  }

  flagValue = null

  @Emit()
  private change(value) {
    return value
  }

  @Watch('data')
  private dataWatch() {
    this.$refs.rightBox.scrollTo({ top: 0 })
    this.initBoxItemTops()
  }

  @Watch('model')
  private modelWatch(value, old) {
    if (value === this.flagValue) {
      return
    }
    this.flagValue = value
    // 滚动的时候会切换值
    this.valueChange(value)
  }

  // 渲染完毕后计算高度
  private mounted() {
    // 同步
    this.flagValue = this.model
    // this.init()
    this.initBoxItemTops()
  }

  private leftScrollToIndex(index: number) {
    let value
    if (this.value) {
      value = this.data[index][this.value]
    } else {
      value = index
    }
    if (value === this.model) return
    // 记录是本组件修改的
    this.flagValue = value
    // 设置当前
    this.change(value)
  }

  valueChange(value) {
    let scrollIndex
    if (this.value) {
      scrollIndex = this.data.find(item => item[this.value] === value)
    } else {
      scrollIndex = value
    }
    const top = this.slideItemTops[scrollIndex]
    // 滚到到指定位置
    this.$refs.rightBox.scrollTo({ top: !top ? top : top + this.preShow })
  }

  // 计算高度
  initBoxItemTops() {
    this.initComponentsHeight()
    this.initRightBoxItemTops()
  }

  /**
   * 获取整个元素高度，默认200
   */
  private componentHeight = 200

  /**
   * 记录右侧每个索引对应的滚动位置，切换下一个菜单
   */
  private slideItemTops: any[] = []

  // 计算组建高度
  private initComponentsHeight() {
    this.$nextTick(() => {
      this.componentHeight = this.$refs.rightBox.clientHeight
    })
  }

  // 计算右侧菜单元素滚动高度
  private initRightBoxItemTops() {
    this.$nextTick(() => {
      this.slideItemTops = []
      const res = this.$refs.slides
      if (res && res.length) {
        const firstTop = res[0].offsetTop
        res.forEach((item, index) => {
          // 需要减去头一个元素到顶部的距离，因为高度是从顶部0算的
          let top = item.offsetTop - firstTop
          // 如果不是第一个，可以设置提前量
          // 因为需要和滚动条位置对比，所以不能出现负数
          // 如果不为0
          if (index) {
            // 获取上一个元素高度, 增加一个逻辑就是多展示一点以后才切换tab
            const lastItemHeight = res[index - 1].clientHeight
            // 如果提前量大于上一个高度
            if (this.preShow > lastItemHeight) {
              // 则提前量为上一个的一半
              top = top - lastItemHeight / 2
            } else {
              top = top - this.preShow
            }
          }
          this.slideItemTops.push(top)
        })
      }
    })
  }

  private rightBoxScroll(e) {
    const scrollTop = e.target.scrollTop
    const scrollIndex = this.slideItemTops.findIndex((item, index) => {
      const height1 = item
      const height2 = this.slideItemTops[index + 1]
      // 这里使用大于等于 是要考虑到第一个为0的情况
      return index === this.slideItemTops.length - 1 || (scrollTop >= height1 && scrollTop < height2)
    })
    this.leftScrollToIndex(scrollIndex)
  }
}
</script>
