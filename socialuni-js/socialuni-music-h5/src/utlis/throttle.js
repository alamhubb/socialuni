export function throttle(fun,time=300){
	let lastTime = 0
	return function(args){
		const nowTime = Date.now()
		const remainTime = time - (nowTime - lastTime)
		if (remainTime<=0) {
			fun(args)
			lastTime = nowTime
		}
		
	}
}
