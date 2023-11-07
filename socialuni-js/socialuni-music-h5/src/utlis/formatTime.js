export function formatTime(times) {
	let date = new Date(times);
	let year = date.getFullYear();
	let month = date.getMonth() + 1; //月份
	let day = date.getDate(); //日
	let hour = function () { //获取小时
		return date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
	}
	let minute = function () { //获取分钟
		return date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
	}
	
	let second = function () { //获取秒数
		return date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
	}
	return year + '-' + month + '-' + day + ' ' + hour() + ':' + minute() + ':' + second()
}