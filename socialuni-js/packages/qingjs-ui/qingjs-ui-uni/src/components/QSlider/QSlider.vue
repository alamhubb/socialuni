<template>
  <div
      class="slider-range"
      :class="{ disabled: disabled }"
      :style="{ paddingLeft: blockSize / 2 + 'px', paddingRight: blockSize / 2 + 'px' }"
  >
    <div class="slider-range-inner" :style="{ height: height + 'px' }">
      <div
          class="slider-bar"
          :style="{
          height: barHeight + 'px',
        }"
      >
        <!-- 背景条 -->
        <div
            class="slider-bar-bg"
            :style="{
            backgroundColor: backgroundColor,
          }"
        ></div>

        <!-- 滑块实际区间 -->
        <div
            class="slider-bar-inner"
            :style="{
            width: ((values[1] - values[0]) / (max - min)) * 100 + '%',
            left: lowerHandlePosition + '%',
            backgroundColor: activeColor,
          }"
        ></div>
      </div>

      <!-- 滑动块-左 -->
      <div
          class="slider-handle-block"
          :class="{ decoration: decorationVisible }"
          :style="{
          backgroundColor: blockColor,
          width: blockSize + 'px',
          height: blockSize + 'px',
          left: lowerHandlePosition + '%',
        }"
          @touchstart="_onTouchStart"
          @touchmove="_onBlockTouchMove"
          @touchend="_onBlockTouchEnd"
          data-tag="lowerBlock"
      ></div>

      <!-- 滑动块-右 -->
      <div
          class="slider-handle-block"
          :class="{ decoration: decorationVisible }"
          :style="{
          backgroundColor: blockColor,
          width: blockSize + 'px',
          height: blockSize + 'px',
          left: higherHandlePosition + '%',
        }"
          @touchstart="_onTouchStart"
          @touchmove="_onBlockTouchMove"
          @touchend="_onBlockTouchEnd"
          data-tag="higherBlock"
      ></div>

      <!-- 滑块值提示 -->
      <div v-if="tipVisible" class="range-tip" :style="lowerTipStyle">{{ format(values[0]) }}</div>
      <div v-if="tipVisible" class="range-tip" :style="higherTipStyle">{{ format(values[1]) }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'


@Component({})
class QSlider extends Vue {
  //滑块区间当前取值
  @Prop({
    type: Array,
    default: function () {
      return [0, 100]
    }
  }) value

  @Prop({
    type: Number,
    default: 0
  }) min
  @Prop({
    type: Number,
    default: 100
  }) max
  @Prop({
    type: Number,
    default: 1
  }) step

  @Prop({
    type: Function,
    default: function (val) {
      return val
    }
  }) format

  @Prop({
    type: Boolean,
    default: false
  }) disabled

  @Prop({
    type: Number,
    default: 50
  }) height

  @Prop({
    type: Number,
    default: 5
  }) barHeight

  @Prop({
    type: String,
    default: '#e9e9e9'
  }) backgroundColor

  @Prop({
    type: String,
    default: '#1aad19'
  }) activeColor
  @Prop({
    type: Number,
    default: 20
  }) blockSize
  @Prop({
    type: String,
    default: '#fff'
  }) blockColor
  @Prop({
    type: Boolean,
    default: true
  }) tipVisible
  @Prop({
    type: Boolean,
    default: false
  }) decorationVisible

  values = []
  startDragPos = 0 // 开始拖动时的坐标位置
  startVal = 0 //开始拖动时较小点的值
  isDragging = false //开始拖动时较小点的值

  created() {
    this.values = [this.min, this.max]
  }

  // 较小点滑块的坐标
  get lowerHandlePosition() {
    return ((this.values[0] - this.min) / (this.max - this.min)) * 100
  }

  // 较大点滑块的坐标
  get higherHandlePosition() {
    return ((this.values[1] - this.min) / (this.max - this.min)) * 100
  }

  get lowerTipStyle() {
    if (this.lowerHandlePosition < 90) {
      return `left: ${this.lowerHandlePosition}%;`
    }
    return `right: ${100 - this.lowerHandlePosition}%;transform: translate(50%, -100%);`
  }

  get higherTipStyle() {
    if (this.higherHandlePosition < 90) {
      return `left: ${this.higherHandlePosition}%;`
    }
    return `right: ${100 - this.higherHandlePosition}%;transform: translate(50%, -100%);`
  }

  @Watch('value', {immediate: true})
  valueWatch(newVal) {
    if (this._isValuesValid(newVal) && (newVal[0] !== this.values[0] || newVal[1] !== this.values[1])) {
      this._updateValue(newVal)
    }
  }

  _updateValue(newVal) {
    // 步长大于区间差，或者区间最大值和最小值相等情况
    if (this.step >= this.max - this.min) {
      throw new RangeError('Invalid slider step or slider range')
    }

    let newValues = []
    if (Array.isArray(newVal)) {
      newValues = [newVal[0], newVal[1]]
    }
    if (typeof newValues[0] !== 'number') {
      newValues[0] = this.values[0]
    } else {
      newValues[0] = Math.round((newValues[0] - this.min) / this.step) * this.step + this.min
    }
    if (typeof newValues[1] !== 'number') {
      newValues[1] = this.values[1]
    } else {
      newValues[1] = Math.round((newValues[1] - this.min) / this.step) * this.step + this.min
    }

    // 新值与原值相等，不做处理
    if (this.values[0] === newValues[0] && this.values[1] === newValues[1]) {
      return
    }
    // 左侧滑块值小于最小值时，设置为最小值
    if (newValues[0] < this.min) {
      newValues[0] = this.min
    }
    // 右侧滑块值大于最大值时，设置为最大值
    if (newValues[1] > this.max) {
      newValues[1] = this.max
    }
    // 两个滑块重叠或左右交错，使两个滑块保持最小步长的间距
    if (newValues[0] >= newValues[1]) {
      // 左侧未动，右侧滑块滑到左侧滑块之左
      if (newValues[0] === this.values[0]) {
        newValues[1] = newValues[0] + this.step
      } else {
        // 右侧未动， 左侧滑块滑到右侧之右
        newValues[0] = newValues[1] - this.step
      }
    }

    this.values = newValues
    this.$emit('change', this.values)
  }

  _onTouchStart(event) {
    if (this.disabled) {
      return
    }

    this.isDragging = true
    const tag = event.target.dataset.tag

    //兼容h5平台及某版本微信
    const e = event.changedTouches ? event.changedTouches[0] : event
    this.startDragPos = e.pageX

    this.startVal = tag === 'lowerBlock' ? this.values[0] : this.values[1]
  }

  _onBlockTouchMove(e) {
    if (this.disabled) {
      return
    }
    this._onDrag(e)
  }

  _onBlockTouchEnd(e) {
    if (this.disabled) {
      return
    }
    this.isDragging = false
    this._onDrag(e)
  }

  _onDrag(event) {
    if (!this.isDragging) {
      return
    }
    const view = uni
        .createSelectorQuery()
        .in(this)
        .select('.slider-range-inner')

    view
        .boundingClientRect(data => {
          const sliderWidth = data.width
          const tag = event.target.dataset.tag
          const e = event.changedTouches ? event.changedTouches[0] : event
          const diff = ((e.pageX - this.startDragPos) / sliderWidth) * (this.max - this.min)
          const nextVal = this.startVal + diff

          if (tag === 'lowerBlock') {
            this._updateValue([nextVal, null])
          } else {
            this._updateValue([null, nextVal])
          }
        })
        .exec()
  }

  _isValuesValid(values) {
    return Array.isArray(values) && values.length === 2
  }
}
export default toNative(QSlider)
</script>

<style scoped>
.slider-range {
  position: relative;
}

.slider-range-inner {
  position: relative;
  width: 100%;
}

.slider-range.disabled .slider-bar-inner {
  opacity: 0.35;
}

.slider-range.disabled .slider-handle-block {
  cursor: not-allowed;
}

.slider-bar {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  transform: translateY(-50%);
}

.slider-bar-bg {
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 10000px;
  z-index: 10;
}

.slider-bar-inner {
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 10000px;
  z-index: 11;
}

.slider-handle-block {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  box-shadow: 0 0 3px 2px rgba(227, 229, 241, 0.5);
  z-index: 12;
}

.slider-handle-block.decoration::before {
  position: absolute;
  content: '';
  width: 3px;
  height: 12px;
  top: 50%;
  left: 29%;
  transform: translateY(-50%);
  background: #eeedf2;
  border-radius: 1px;
  z-index: 13;
}

.slider-handle-block.decoration::after {
  position: absolute;
  content: '';
  width: 1px;
  height: 12px;
  top: 50%;
  right: 29%;
  transform: translateY(-50%);
  background: #eeedf2;
  border-radius: 1px;
  z-index: 13;
}

.range-tip {
  position: absolute;
  top: 0;
  font-size: 12px;
  color: #666;
  transform: translate(-50%, -100%);
}
</style>
