<template>
	<view class="amap-container">
		<view :change:prop="amap.updateEcharts" id="amap"></view>
		<view style="margin: 30rpx;">
			<button type="primary" @click="amap.onClick">定位当前位置</button>
		</view>
		<view style="margin: 15rpx;">当前位置信息：lng:{{ currentPosition.lng }} , lat:{{currentPosition.lat}}</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			markerList: [],
			currentPosition: {}
		}
	},
	mounted() {
		
	},
	methods: {
		//地图点击回调事件
		onViewClick(params) {
			this.currentPosition = params.currentPosition
		}
	}
}
</script>

<script module="amap" lang="renderjs">
import config from '@/components/ITkoala-amap/config.js'

export default {
	data() {
		return {
			map: null,
			ownerInstanceObj: null //service层对象
		}
	},
	mounted() {
		if (typeof window.AMap === 'function') {
			this.initAmap()
		} else {
			// 动态引入较大类库避免影响页面展示
			const script = document.createElement('script')
			script.src = 'https://webapi.amap.com/maps?v=1.4.15&key=' + config.WEBAK
			script.onload = this.initAmap.bind(this)
			document.head.appendChild(script)
		}
	},
	methods: {
		initAmap() {
			this.map = new AMap.Map('amap', {
				resizeEnable: true
			})
		},
		updateEcharts(newValue, oldValue, ownerInstance, instance) {
			// 监听 service 层数据变更
			this.ownerInstanceObj = ownerInstance
		},
		onClick(event, ownerInstance) {
			// 创建一个 Marker 实例：
			let marker = new AMap.Marker({
			    position: this.map.getCenter() // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
			})
			// 将创建的点标记添加到已有的地图实例：
			this.map.add(marker)

			// 调用 service 层的方法
			ownerInstance.callMethod('onViewClick', {
				currentPosition: this.map.getCenter()
			})
		}
	}
}
</script>

<style lang="scss" scoped>
#amap {
	width: 100%;
	height: 600rpx;
}
</style>
