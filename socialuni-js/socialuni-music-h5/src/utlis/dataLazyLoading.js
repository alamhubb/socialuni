export function dataLazyLoading(dom){
	return new Promise(resolve => {
		const io = new IntersectionObserver(([{isIntersecting}]) => {
			if (isIntersecting){
				resolve()
				io.unobserve(dom.value)
			}
		})
		io.observe(dom.value)
	})
}
