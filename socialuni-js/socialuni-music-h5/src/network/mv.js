import {request} from "@/network/index.js";


export	function getRecommendMV(){
	return request({
		url:'/personalized/mv'
	})
}


export function getFM(){
	return request({
		url:`/personal_fm`
	})
}

export function getMvList(){
	return request({
		url:`/mv/sublist`
	})
}