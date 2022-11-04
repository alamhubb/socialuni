<template>
  <view
    class="circle-progress-bar"
    :style="{
      height: size + cunit,
      width: size + cunit,
    }"
  >
    <view
      class="container"
      :style="{
        background: border_back_color,
        height: size + cunit,
        width: size + cunit,
      }"
    >
      <view
        style="width: 100%; height: 100%"
        :style="{
          transform: `rotate(${start * 360}deg)`,
        }"
      >
        <view
          class="left"
          :style="{
            clip: `rect(0px, ${halfSize + cunit}, ${size + cunit}, 0px)`,
          }"
        >
          <view
            class="inner"
            :style="{
              background: border_color,
              clip: `rect(0px, ${size + cunit}, ${size + cunit}, ${
                halfSize + cunit
              })`,
              transform: `rotate(${left_deg}deg)`,
              transition: animate ? `all ${left_time}ms` : '',
              'transition-timing-function':
                first == 'left' ? 'ease-in' : 'ease-out',
            }"
          ></view>
        </view>
        <view
          class="right"
          :style="{
            clip: `rect(0px, ${size + cunit}, ${size + cunit}, ${
              halfSize + cunit
            })`,
          }"
        >
          <view
            class="inner"
            :style="{
              background: border_color,
              clip: `rect(0px, ${halfSize + cunit}, ${size + cunit}, 0)`,
              transform: `rotate(${right_deg}deg)`,
              transition: animate ? `all ${right_time}ms` : '',
              'transition-timing-function':
                first == 'right' ? 'ease-in' : 'ease-out',
            }"
          ></view>
        </view>
        <view
          v-show="pro > 0"
          class="point"
          :style="{
            width: border_width + cunit,
            height: border_width + cunit,
            left: `calc(50% - ${border_width / 2}${cunit})`,
            background: border_color,
          }"
        ></view>
        <view
          v-show="pro > 0"
          class="point"
          :style="{
            width: border_width + cunit,
            height: border_width + cunit,
            left: `calc(50% - ${border_width / 2}${cunit})`,
            transform: `rotate(${point_deg}deg)`,
            transition: animate
              ? `all ${cur_set == 'left' ? left_time : right_time}ms`
              : '',
            'transform-origin': `${border_width / 2}${cunit} ${
              size / 2
            }${cunit}`,
            'transition-timing-function':
              cur_set == 'left'
                ? first == 'left'
                  ? 'ease-in'
                  : 'ease-out'
                : first == 'right'
                ? 'ease-in'
                : 'ease-out',
            background: border_color,
          }"
        ></view>
      </view>
      <view
        class="content abs-center"
        style="border-radius: 50%"
        :style="{
          background: background,
          width: contentSize + cunit,
          height: contentSize + cunit,
        }"
      >
        <view class="abs-center">
          <slot :pro="pro"></slot>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  name: "circle-progress-bar",
  props: {
    //进度 0-1
    pro: {
      type: Number,
      default: 0,
    },
    //起始位置 0-1
    start: {
      type: Number,
      default: 0,
    },
    //圆形大小
    size: {
      type: Number,
      default: 100,
    },
    //线宽度
    border_width: {
      type: Number,
      default: 5,
    },
    //线颜色
    border_color: {
      type: String,
      default: "#07C160",
    },
    //线背景色
    border_back_color: {
      type: String,
      default: "#DDD",
    },
    //中心内容背景色
    background: {
      type: String,
      default: "#FFF",
    },
    //单位
    unit: {
      type: String,
      default: "rpx",
    },
    //是否启用动画
    animate: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      firstLoad: true,
      cur_pro: 0,
      left_deg: 0,
      right_deg: 0,
      point_deg: 0,
      left_time: 0,
      right_time: 0,
      first: "",
      cur_set: "",
      tid: 0,
    };
  },
  watch: {
    pro(val) {
      this.setPro(val);
    },
  },
  computed: {
    halfSize() {
      return this.size / 2;
    },
    contentSize() {
      return this.size - this.border_width * 2;
    },
    cunit() {
      if (this.unit === "upx") {
        return "rpx";
      }
      return this.unit;
    },
  },
  mounted() {
    this.setPro(this.pro);
  },
  methods: {
    setPro(pro) {
      this.cur_set = "";

      this.$nextTick(() => {
        this.rePro(pro);
      });
    },
    rePro(pro) {
      const time = 300;
      let left = 0;
      let right = 0;
      let first = this.cur_pro > 0.5 ? "left" : "right";
      if (this.cur_pro == 0.5) {
        first = pro > 0.5 ? "left" : "right";
      }
      if (pro > 0.5) {
        left = (pro - 0.5) * 2 * 180;
        right = 180;
      } else {
        left = 0;
        right = pro * 2 * 180;
      }

      let dis_left = this.left_deg - left;
      let dis_right = this.right_deg - right;
      let dis_all = Math.abs(dis_left) + Math.abs(dis_right);
      let left_time = (Math.abs(dis_left) / dis_all) * time;
      let right_time = (Math.abs(dis_right) / dis_all) * time;

      if (this.firstLoad || !this.animate) {
        this.firstLoad = false;
        this.first = first;
        this.left_deg = left;
        this.right_deg = right;
        this.left_time = left_time;
        this.right_time = right_time;
        this.cur_pro = pro;
        this.rePointDeg();
        return;
      }

      //console.log('first', first)
      //console.log('time', left_time, right_time)
      if (first === "left") {
        if (right_time > 0) {
          this.cur_set = "left";
          this.left_deg = left;
          this.rePointDeg();
          clearTimeout(this.tid);
          this.tid = setTimeout(() => {
            this.cur_set = "right";
            this.right_deg = right;
            this.rePointDeg();
          }, left_time);
        } else {
          this.cur_set = "left";
          this.left_deg = left;
          this.right_deg = right;
          this.rePointDeg();
        }
      } else {
        if (left_time > 0) {
          this.cur_set = "right";
          this.right_deg = right;
          this.rePointDeg();
          clearTimeout(this.tid);
          this.tid = setTimeout(() => {
            this.cur_set = "left";
            this.left_deg = left;
            this.rePointDeg();
          }, right_time);
        } else {
          this.cur_set = "right";
          this.left_deg = left;
          this.right_deg = right;
          this.rePointDeg();
        }
      }

      this.first = first;
      this.left_time = left_time;
      this.right_time = right_time;
      this.cur_pro = pro;
    },
    rePointDeg() {
      this.point_deg = this.left_deg + this.right_deg;
    },
  },
};
</script>

<style lang="scss">
$size: 200px;
$half-size: $size / 2;
.container {
  position: relative;
  border-radius: 50%;
}

.left,
.right {
  position: absolute;
  width: 100%;
  height: 100%;

  .inner {
    position: absolute;
    width: 100%;
    height: 100%;
    background: #ddd;
    border-radius: 50%;
  }
}

.left {
  clip: rect(0px, $half-size, $size, 0px);
  .inner {
    clip: rect(0px, $size, $size, $half-size);
  }
}
.right {
  clip: rect(0px, $size, $size, $half-size);
  .inner {
    clip: rect(0px, $half-size, $size, 0);
  }
}
.point {
  z-index: 3;
  top: 0upx;
  border-radius: 50%;
  position: absolute;
}
.abs-center {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>