<template>
  <view class="map">
    <view class="statusBar customBar"></view>
    <u-navbar class="navbar" autoBack>
      <view class="navbar-center" slot="center"> 位置 </view>
      <view class="navbar-right" slot="right">
        <view class="right-item" @click="sendMessage">确定</view>
      </view>
    </u-navbar>
    <map
      id="map"
      class="mapContainer"
      :show-location="true"
      :latitude="latitude"
      :longitude="longitude"
      :markers="markers"
      @updated="updated"
      @regionchange="regionchange"
    />
    <view class="search">
      <view class="search-top">
        <u-search
          placeholder="搜索"
          v-model="search.param"
          :showAction="false"
          shape="square"
          @search="searchAddress"
        />
      </view>
      <view class="search-result">
        <view
          class="search-result-item"
          v-for="(item, index) in search.list"
          :key="index"
        >
          <view class="title">
            <text>
              {{ item.title }}
            </text>
            <u-icon
              v-show="search.id === item.id"
              class="icon"
              name="checkbox-mark"
              color="#1D6BED"
              size="16"
            />
          </view>
          <view class="des">{{ item.desc }}</view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      userID: "",
      groupID: "",
      latitude: 23.099994,
      longitude: 113.32452,
      markers: [
        {
          id: 1,
          title: "",
          latitude: 23.099994,
          longitude: 113.32452,
          iconPath: "/static/images/map/point.png",
          width: 36,
          height: 36,
        },
      ],
      search: { param: "", list: [], id: "" },
      isSend: false,
    };
  },
  methods: {
    init() {
      uni.getLocation({
        type: "gcj02",
        geocode: true,
        success: (res) => {
          console.log(res);
          const { longitude, latitude, address } = res;
          this.reset(latitude, longitude);
          this.setDefaultAddress(address);
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    updated() {},
    setDefaultAddress(addressInfo = null) {
      let address = "未知位置";
      let desc = "";
      if (addressInfo) {
        const { city, district, street, streetNum, poiName } = addressInfo;
        address = `${poiName}`;
        desc = `${city}${district}${street}${streetNum}`;
      }
      this.search.id = "1";
      this.search.list = [
        {
          id: "1",
          title: address,
          desc,
          latitude: this.latitude,
          longitude: this.longitude,
        },
      ];
    },
    searchAddress() {
      this.$toast("暂不支持此功能");
    },
    regionchange(e) {
      // if (e.type === "end") {
      //   const { latitude, longitude } = e.detail.centerLocation;
      //   this.reset(latitude, longitude);
      // }
    },
    reset(latitude, longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
      this.markers[0].latitude = latitude;
      this.markers[0].longitude = longitude;
    },
    sendMessage() {
      if (this.isSend) return;
      this.isSend = true;
      const { title, latitude, longitude } = this.currentLocationItem;
      // #ifdef APP-PLUS
      const content = this.$im.createLocationMessage(
        this.operationID,
        title,
        latitude,
        longitude
      );
      this.$im.sendMessage(
        this.operationID,
        content,
        this.userID,
        this.groupID,
        {
          title: "你有一条新消息",
          desc: "",
          ex: "",
          iOSPushSound: "+1",
          iOSBadgeCount: true,
        }
      );
      this.$toast("发送成功");
      setTimeout(() => {
        this.isSend = false;
        uni.navigateBack();
      }, 1000);
      // #endif
    },
  },
  onLoad(param) {
    this.userID = param.userID;
    this.groupID = param.groupID;
    this.init();
  },
  onReady() {
    uni.createMapContext("map", this).$getAppMap();
  },
  computed: {
    ...mapGetters(["operationID"]),
    currentLocationItem() {
      return this.search.list.filter((i) => i.id === this.search.id)[0];
    },
  },
};
</script>

<style lang="scss" scoped>
$paddingLeft: 40rpx;
.map {
  width: 100%;
  height: 100vh;
  .mapContainer {
    width: 100%;
    height: 400rpx;
  }
  .search {
    height: calc(100vh - 400rpx);
    &-top {
      padding: $paddingLeft;
      border-bottom: 2rpx solid #f1f1f1;
    }
    &-result {
      &-item {
        padding: 20rpx $paddingLeft;
        border-bottom: 2rpx solid #f1f1f1;
        .title {
          font-size: 36rpx;
          color: #333333;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          display: flex;
          justify-content: space-between;
          align-items: center;
          flex-direction: row;
          .icon {
            margin-left: 12rpx;
            flex-shrink: 0;
          }
        }
        .des {
          // min-height: 38rpx;
          font-size: 28rpx;
          color: #999999;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
      }
    }
  }
}
</style>