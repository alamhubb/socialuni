<template>
  <view class="MessageItem">
    <slot />
    <view
      class="MessageItem-item"
      v-for="(contentItem, index) in list"
      :key="index"
      :style="{
        'font-size': fontSize,
        color: color,
      }"
    >
      <text v-if="hasDraftText && index === 0" class="draftText"> [草稿] </text>
      <text v-if="contentItem.type === 'text'">
        {{ contentItem.content }}
      </text>
      <text v-if="contentItem.type === 'atUser'" class="atUser">
        @{{ contentItem.nickname }}
      </text>
      <image
        v-else-if="contentItem.type === 'emoji'"
        class="image"
        :src="contentItem.src"
      />
    </view>
  </view>
</template>

<script>
export default {
  props: {
    list: {
      type: Array,
      default: () => [],
    },
    fontSize: {
      type: String,
      default: "28rpx",
    },
    color: {
      type: String,
      default: "#333",
    },
    hasDraftText: {
      type: Boolean,
      default: false,
    },
  },
  created(){
  },
};
</script>

<style lang="scss" scoped>
.MessageItem {
  display: flex;
  flex-direction: row;
  align-items: center;
  flex-wrap: wrap;
  min-height: 42rpx;
  &-item {
    display: flex;
    word-break: break-all;
    height: 36rpx;
    line-height: 36rpx;
    .draftText {
      color: #f44038;
    }
    .atUser {
      color: #3e44ff;
    }
    .image {
      width: 44rpx;
      height: 34rpx;
    }
  }
}
</style>