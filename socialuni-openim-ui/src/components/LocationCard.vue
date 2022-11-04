<template>
  <view class="LocationCard">
    <view class="LocationCard-title">
      {{ locationElem.description }}
    </view>
    <mapItem v-if="mapId" :mapId="mapId" :locationElem="locationElem" />
  </view>
</template>

<script>
import mapItem from "@/components/ITkoala-amap";
export default {
  components: { mapItem },
  props: {
    locationElem: {
      type: Object,
      default: () => {
        return { description: "", latitude: 0, longitude: 0 };
      },
    },
  },
  data() {
    return {
      mapId: "",
    };
  },
  methods: {
    init() {
      const time = new Date().getTime().toString();
      this.mapId = `amap-${time}`;
    },
  },
  computed: {
    markers() {
      return [
        {
          id: 1,
          title: "",
          latitude: this.locationElem.latitude,
          longitude: this.locationElem.longitude,
          iconPath: "/static/images/map/point.png",
          width: 24,
          height: 24,
        },
      ];
    },
  },
  created() {
    this.init();
  },
};
</script>

<style lang="scss" scoped>
.LocationCard {
  position: relative;
  width: 420rpx;
  &-title {
    padding: 8rpx;
    font-size: 28rpx;
    color: #333333;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-bottom: 12rpx;
  }
}
</style>