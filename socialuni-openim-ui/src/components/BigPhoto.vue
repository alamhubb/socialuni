<template>
  <u-overlay :show="show" @click="show = false">
    <view class="BigPhoto">
      <image class="image" :src="src" mode="aspectFit" />
    </view>
  </u-overlay>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      src: "",
    };
  },
  methods: {
    init(src, bigPhotoStyle) {
      this.src = src;
      this.show = true;
    },
  },
};
</script>
<style lang="scss" scoped>
.BigPhoto {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  .image {
    width: 100%;
    height: 100%;
  }
}
</style>