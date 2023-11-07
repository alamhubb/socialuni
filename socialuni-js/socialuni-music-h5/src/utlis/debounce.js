export function debounce(fun,time=500) {
	let timer
	return function (...args) {
		if(timer)clearTimeout(timer)
		timer = setTimeout(() => {
			fun.apply(this,args)
		},time)
	}
}