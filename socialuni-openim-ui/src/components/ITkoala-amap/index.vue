<template>
  <view
    class="amap"
    :prop="prop"
    :change:prop="amap.updateEcharts"
    :id="mapId"
  />
</template>
<script>
export default {
  props: {
    mapId: {
      type: String,
      default: "",
    },
    locationElem: {
      type: Object,
      default: () => {
        return { latitude: 0, longitude: 0 };
      },
    },
  },
  data() {
    return {};
  },
  computed: {
    prop() {
      return {
        mapId: this.mapId,
        locationElem: this.locationElem,
      };
    },
  },
};
</script>
<script module="amap" lang="renderjs">
import config from '@/components/ITkoala-amap/config.js'
export default {
	data() {
		return {
			map: null,
			ownerInstanceObj: null, //service层对象
		}
	},
	mounted() {
		if (typeof window.AMap === 'function') {
			this.initAmap()
		} else {
			// 动态引入较大类库避免影响页面展示
			const script = document.createElement('script')
			script.src = 'https://webapi.amap.com/maps?v=1.4.15&key=' + config.JSAPIAK + '&plugin=AMap.Autocomplete,AMap.PlaceSearch'
			script.onload = this.initAmap.bind(this)
			document.head.appendChild(script)
		}
		
	},
	methods: {
		initAmap() {
			// 初始化
			this.map = new AMap.Map(this.prop.mapId, {
				resizeEnable: false,
				dragEnable: false,
				center: [this.prop.locationElem.longitude, this.prop.locationElem.latitude],
				zoom: 18
			})
			let marker = new AMap.Marker({
				position: this.map.getCenter()
			})
			this.map.add(marker)
		},
		updateEcharts(newValue, oldValue, ownerInstance, instance) {
			// 监听 service 层数据变更
			this.ownerInstanceObj = ownerInstance
		},
	}
}
</script>

<style lang="scss" scoped>
.amap {
  width: 420rpx;
  height: 180rpx;
}
</style>
