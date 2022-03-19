<template>
  <scroll-view :scroll-x="true" class="tabUuid overflow-hidden" :class="[uuid]" :scroll-left="leftBoxScrollLeft">
    <div class="row-nowrap position-relative">
      <div class="barUuid row-all-center" :class="[uuid,isBar?'fixed-index flex-1':'flex-none']"
           v-for="(tab,index) in tabs" @click="input(index)" :key="tab.name">
        <div class="lineUuid row-all-center" :class="[uuid,index===value?activeClass:unActiveClass]">
          <slot v-bind:tab="tab" v-bind:index="index" v-bind:value="value">

          </slot>
        </div>
        <div hover-class="uni-list-cell-hover" class="col-center" :class="[index===value?activeClass:unActiveClass]">
          <slot name="icon" v-bind:tab="tab" v-bind:index="index" v-bind:value="value"></slot>
        </div>
      </div>
      <div class="position-absolute" :style="[tabSlideStyle]">
        <div class="position-absolute"
             :style="{width:barWidth+'px',height:barHeight+'px'}"
             :class="[isBar?'bg-white bd-radius':'bg-theme t27 bd-radius-20']">
        </div>
      </div>
    </div>
  </scroll-view>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import CommonUtil from '../../../socialuni/utils/CommonUtil'
import TalkTabVO from '@/socialuni/model/talk/TalkTabVO'
import NodesRef = UniApp.NodesRef
import SelectorQuery = UniApp.SelectorQuery

@Component
export default class QTabs extends Vue {
  //line、bar、card
  @Prop({
    default: 'line',
    type: String
  }) type: 'line' | 'bar'

  get isBar () {
    return this.type === 'bar'
  }

  componentWeight = 0
  leftBoxScrollLeft = 0

  get isLine () {
    return this.type === 'line'
  }

  //唯一id值
  uuid: string = CommonUtil.getClassUUID()

  @Model('input') readonly value: number
  @Prop({ default: [] }) readonly tabs: TalkTabVO[]

  @Watch('value')
  valueWatch (val, oldVal) {
    if (val !== oldVal) {
      this.$nextTick(() => {
        this.getTabRect()
        this.leftBoxScrollLeft = this.tabScrollLefts[this.value]
      })
    }
  }

  @Watch('tabs')
  tabsWatch () {
    if (this.tabs.length) {
      this.$nextTick(() => {
        this.getTabRect()
        this.leftBoxScrollLeft = this.tabScrollLefts[this.value]
      })
    }
  }

  // @Prop({ default: '50' }) readonly barWidth: string
  get activeClass () {
    if (this.isBar) {
      return ['color-main', 'font-bold']
    } else {
      return ['color-theme', 'font-bold']
    }
  }

  get unActiveClass () {
    if (this.isBar) {
      return ['color-white']
    } else {
      return ['color-main']
    }
  }

  tabItemLefts: number[] = []
  tabScrollLefts: number[] = []
  barWidth: number = 0
  barHeight: number = 0

  //判断是否已加载过
  firstLoadAfter: boolean = false

  //滑块样式
  get tabSlideStyle () {
    return {
      transform: 'translate(' + this.tabItemLefts[this.value] + 'px)',
      //首次不开启动画
      'transition-duration': this.firstLoadAfter ? '0.5s' : '0'
    }
  }

  @Emit()
  input (index: number) {
    return index
  }

  mounted () {
    this.init()
  }

  // 设置一个init方法，方便多处调用
  init () {
    //等待元素渲染
    this.initComponentsWeight()
    this.getTabRect()
  }

  //计算组建高度
  initComponentsWeight () {
    //获取整个组件的高度
    const query: SelectorQuery = uni.createSelectorQuery().in(this)
    const nodeBox: NodesRef = query.select('.' + this.uuid + '.tabUuid')
    nodeBox.boundingClientRect((res) => {
      if (res) {
        this.componentWeight = res.width
      } else {
        CommonUtil.delayTime(100).then(() => {
          this.initComponentsWeight()
        })
      }
    }).exec()
  }

  // 查询tab的布局信息
  getTabRect () {
    // 创建节点查询
    const query = uni.createSelectorQuery().in(this)
    // 历遍所有tab，这里是执行了查询，最终使用exec()会一次性返回查询的数组结果
    // 只要size和rect两个参数
    const uuid = '.barUuid'
    /*if (this.isBar) {
      uuid = '.barUuid'
    } else {
      uuid = '.lineUuid'
    }*/
    query.selectAll(`.${this.uuid}${uuid}`).boundingClientRect((res: any) => {
      this.tabItemLefts = []
      this.tabScrollLefts = []
      //如果元素还没加载出来，延迟0.1秒继续加载
      if (res && res.length) {
        if (this.isBar) {
          this.barWidth = res[0].width
          this.barHeight = res[0].height
        } else {
          this.barWidth = 20
          this.barHeight = 3
        }
        res.forEach(item => {
          //设置每个tab滑块对应的位置
          if (this.isBar) {
            this.tabItemLefts.push(item.left - res[0].left)
          } else {
            //自己宽度一般，
            this.tabItemLefts.push(item.left + item.width / 2 - (Number(this.barWidth) / 2) - res[0].left)
            const left = item.left + item.width / 2 - this.componentWeight / 2 - res[0].left
            this.tabScrollLefts.push(left)
            // this.tabItemLefts.push(item.left + item.width / 2 - (Number(this.tabWidth) / 2) / 2 - res[0].left)
          }
        })
        //首次完成2秒后切换首次加载状态，因为首次加载不需要动画，首次加载后开启动画
        CommonUtil.delayTime(2000).then(() => {
          this.firstLoadAfter = true
        })
      } else {
        CommonUtil.delayTime(100).then(() => {
          this.getTabRect()
        })
      }
    })
    query.exec()
  }
}
</script>
