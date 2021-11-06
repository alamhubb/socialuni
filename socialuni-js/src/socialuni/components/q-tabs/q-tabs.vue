<template>
  <view class="flex-row position-relative overflow-x-auto">
    <div class="index-1000" :class="[uuid,isBar?'flex-1':'flex-none']" v-for="(tab,index) in tabs" @click="input(index)"
         :key="index">
      <slot v-bind:tab="tab" v-bind:index="index" v-bind:value="value">

      </slot>
    </div>
    <div class="position-absolute" :style="[tabSlideStyle]">
      <slot name="active">
        <div class="position-absolute h30 bg-white bd-radius" :style="{width:tabWidth+'px'}">

        </div>
      </slot>
    </div>
    <!--    <view class="row-nowrap flex-1 mr-smm flex-none" v-for="(tab,index) in tabs" @click="input(index)" :key="index">
          <view class="index-1000 w100p" :class="[uuid]">
            <slot v-bind:tab="tab" v-bind:index="index" v-bind:value="value">

            </slot>
          </view>
          <view hover-class="uni-list-cell-hover" class="col-center">
            <slot name="icon" v-bind:tab="tab" v-bind:index="index" v-bind:value="value"></slot>
          </view>
        </view>
    &lt;!&ndash;     :style="[tabSlideStyle]"&ndash;&gt;
       -->
  </view>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import CommonUtil from '../../utils/CommonUtil'

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

  get isLine () {
    return this.type === 'line'
  }

  //唯一id值
  uuid: string = CommonUtil.getClassUUID()

  @Model('input') readonly value: number
  @Prop({ default: [] }) readonly tabs: any[]
  @Prop({ default: '50' }) readonly barWidth: string
  activeClass: string[] = ['active', 'tab-line']

  tabItemLefts: number[] = [0]
  tabWidth: number = 0

  //判断是否已加载过
  firstLoadAfter: boolean = false

  //滑块样式
  get tabSlideStyle () {
    let barWidth
    if (this.isBar) {
      barWidth = Number(this.tabWidth) / 2
    } else {
      barWidth = Number(this.tabWidth) / 2
      // barWidth = Number(this.barWidth) / 2
    }
    return {
      transform: 'translate(' + this.tabItemLefts[this.value] + 'px)',
      //首次不开启动画
      'transition-duration': this.firstLoadAfter ? '0.5s' : '0',
      width: barWidth + 'px'
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
    this.getTabRect()
  }

  // 查询tab的布局信息
  getTabRect () {
    console.log(this.uuid)
    // 创建节点查询
    const query = uni.createSelectorQuery().in(this)
    // 历遍所有tab，这里是执行了查询，最终使用exec()会一次性返回查询的数组结果
    // 只要size和rect两个参数
    query.selectAll(`.${this.uuid}`).boundingClientRect((res: any) => {
      this.tabItemLefts = []
      //如果元素还没加载出来，延迟0.1秒继续加载
      if (res && res.length) {
        this.tabWidth = res[0].width
        res.forEach(item => {
          //设置每个tab滑块对应的位置
          if (this.isBar) {
            this.tabItemLefts.push(item.left - res[0].left)
          } else {
            //自己宽度一般，
            this.tabItemLefts.push(item.left + item.width / 2 - (Number(this.tabWidth) / 2) - res[0].left)
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
