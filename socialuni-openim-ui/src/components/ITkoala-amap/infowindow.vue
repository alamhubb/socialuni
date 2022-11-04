<template>
	<view class="amap-container">
		<view :prop="markerList" :change:prop="amap.updateEcharts" id="amap"></view>
		<view style="display: none;" id="infoWindow">
			<view class="infoWindow-wrap">
				<view class="infoWindow-content">
					<text class="infoWindow-text">当前点击的对象的index值为：{{ dataIndex }}</text>
					<image class="close" src="/static/ITkoala-amap/close.png" mode="widthFix"></image>
				</view>
				<view class="sharp">
					<image src="/static/ITkoala-amap/sharp.png" mode="widthFix"></image>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
const start = 'static/ITkoala-amap/start.png'
export default {
	data() {
		return {
			markerList: [],
			dataIndex: ''
		}
	},
	mounted() {
		this.$nextTick(() => {
			this.getMapData()
		})
	},
	methods: {
		// 模拟从后台获取地图数据
		getMapData() {
			this.markerList = [
				{
					lat: 39.908775,
					lng: 116.406315,
					icon: start
				},
				{
					lat: 39.973253,
					lng: 116.473195,
					icon: start
				},
				{
					lat: 39.953253,
					lng: 116.453195,
					icon: start
				}
			]
		},
		//地图点击回调事件
		onViewClick(params) {
			this.dataIndex = params.dataIndex
		}
	}
}
</script>

<script module="amap" lang="renderjs">
import config from '@/components/ITkoala-amap/config.js'

const selectedStart = 'static/ITkoala-amap/selectedStart.png' //选中的图片

export default {
	data() {
		return {
			map: null,
			ownerInstanceObj: null, //service层对象
			currentItem: null //当前点击的对象
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
				resizeEnable: true,
				center: [116.397428, 39.90923],
				zooms: [4, 18], //设置地图级别范围
				zoom: 10
			})

			this.initMarkers()
		},
		//初始化标记点
		initMarkers() {
			let prevMarker = null
			let prevIcon = null
			this.markerList.forEach((item, index) => {

				if(!!item.icon){
					//添加点标记
					let marker = new AMap.Marker({
						position: new AMap.LngLat(item.lng, item.lat),
						offset: new AMap.Pixel(-13, -30),
						icon: item.icon
					})

					marker.on('click', (e) => {
						this.currentItem = item
						if(!!prevMarker){
							prevMarker.setIcon(prevIcon)
						}
						prevIcon = item.icon
						prevMarker = marker
						marker.setIcon(selectedStart)
						this.dataIndex = index
						this.onClick(this.ownerInstanceObj)
						setTimeout(() => {
							this.showInfoWindow()
						},100)

					})

					this.map.add(marker)
				}

			})
		},
		//显示信息窗体
		showInfoWindow(){
			let element = document.getElementById('infoWindow')
			let content = element.innerHTML
			let infoWindow = new AMap.InfoWindow({
			    isCustom: true,  //使用自定义窗体
			    content: this.createInfoWindow(content),
			    offset: new AMap.Pixel(16, -45)
			})

			infoWindow.open(this.map, new AMap.LngLat(this.currentItem.lng, this.currentItem.lat))
		},
		//构建自定义信息窗体
		createInfoWindow(content) {
		    let info = document.createElement('div')
		    info.innerHTML = content

			info.onclick = (ev) => {
				let target = (ev.target && ev.target.currentSrc) || null
				if(!!target && target.includes('close.png')){
					this.map.clearInfoWindow()
				}
			}

			/* 使用官方的创建关闭按钮 */
			/* let closeX = document.createElement("img")
			closeX.src = "https://webapi.amap.com/images/close2.gif"
			closeX.style.position = "absolute"
			closeX.style.right =  '5px'
			closeX.style.top =  '5px'

			closeX.onclick = () => {
				this.map.clearInfoWindow()
			}

			info.appendChild(closeX) */

		    return info
		},
		updateEcharts(newValue, oldValue, ownerInstance, instance) {
			// 监听 service 层数据变更
			this.ownerInstanceObj = ownerInstance
		},
		onClick(ownerInstance) {
			// 调用 service 层的方法
		 	ownerInstance.callMethod('onViewClick', {
				dataIndex: this.dataIndex
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

.infoWindow-wrap {
	position: relative;
	background: #fff;
	
	.infoWindow-content{
		padding: 30rpx;
		.infoWindow-text {
			color: #f00;
		}
		
		.close {
			width: 32rpx;
			height: 32rpx;
			position: absolute;
			top: -25rpx;
			right: -15rpx;
		}
	}
	
	.sharp{
		width: 30rpx;
		height: 23rpx;
		position: absolute;
		bottom: -23rpx;
		left: 0;
		right: 0;
		margin: auto;
		image{
			width: 100%;
			height: 100%;
			vertical-align: top;
		}
	}
}
</style>
