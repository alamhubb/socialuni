export function formatNumber(val){
	// 数字过万的处理
		let num = 0
		if (val > 99999999) {
			num = Math.round(Math.floor(val / 100000000)) + '亿'
		} else if (val > 9999){
			num = Math.round(Math.floor(val / 10000)) + '万'
		}else {
			num = val
		}
		return num
}