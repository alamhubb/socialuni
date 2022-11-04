<!--
<template>
  <view
    :class="['Avatar', isError ? 'Avatar-error' : '']"
    :style="{
      width: size,
      height: size,
    }"
    @touchstart.stop="touchstart"
    @touchend.stop="touchend"
  >
    <u-image
      class="Avatar-image"
      :src="src2"
      :width="size"
      :height="size"
      @error="errorMessage"
    >
      <view
        slot="error"
        :class="[
          'Avatar-image-error',
          isGroup ? 'Avatar-image-error-group' : '',
        ]"
        :style="{
          height: size,
          'line-height': size,
          'font-size': fontSize,
        }"
      >
        {{ shortName }}
      </view>
    </u-image>
  </view>
</template>

<script>
export default {
  props: {
    src: {
      type: String,
      default: "",
    },
    size: {
      type: String,
      default: "48px",
    },
    fontSize: {
      type: String,
      default: "32rpx",
    },
    name: {
      type: String,
      default: "",
    },
    isGroup: {
      type: Boolean,
      default: false,
    },
    isGroupChat: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      isError: false,
      tapData: {
        //区分点击事件、长按事件
        startTime: null,
        clickTime: 140, //间隔小于100点击事件
        longtapTime: 200, //间隔大于200长按事件
        longtapTimer: null,
      },
    };
  },
  methods: {
    errorMessage() {
      this.isError = true;
    },
    onClick() {
      this.$emit("click");
    },
    touchstart() {
      this.tapData.startTime = new Date().getTime();
      this.tapData.longtapTimer = setTimeout(() => {
        this.$emit("createAtMessage");
      }, this.tapData.longtapTime);
    },
    touchend() {
      const endTime = new Date().getTime();
      const d = endTime - this.tapData.startTime;
      if (d <= this.tapData.clickTime) {
        this.onClick();
      } else if (d >= this.tapData.longtapTime && this.isGroupChat) {
        // this.$emit("createAtMessage");
      }
    },
  },
  computed: {
    src2() {
      return this.src || "error";
    },
    shortName() {
      const name = this.name.substr(0, 2);
      const enNameReg = /[A-Za-z0-9]$/g; //是否英文名
      if (this.isGroup) {
        return this.name;
      } else if (enNameReg.test(name)) {
        return name;
      } else {
        return name.substr(0, 1);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.Avatar {
  border-radius: 12rpx;
  box-sizing: border-box;
  overflow: hidden;
  flex-shrink: 0;
  &-image {
    ::v-deep .u-image__error {
      background: transparent;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    &-error {
      width: 100%;
      overflow: hidden;
      text-align: center;
      font-size: 32rpx;
      font-weight: 600;
      color: #ffffff;
      margin: 0 16rpx;
      white-space: nowrap;
      &-group {
        font-size: 24rpx;
        line-height: 24rpx !important;
        height: auto !important;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        white-space: normal;
        margin: 0 4rpx;
      }
    }
  }
  &-error {
    background-color: #5496eb;
  }
}
</style>-->
