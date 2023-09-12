import {request} from "@/network/index";


export function getBanner(){
	return request({
		url:'/banner'
	})
}

//推荐歌单
export function getSongList(num=9){
	return request({
		url:`/personalized?limit=${num}`
	})
}

//每日歌曲推荐
export function getRecommendSongs(){
	return request({
		url:'/recommend/songs'
	})
}

//歌单详情
export function getSongArray(id){
	return request({
		url:'/playlist/detail',
		params:{
			id
		}
	})
}

//歌单详情
export function getSongListDynamic(id){
	return request({
		url:'/playlist/detail',
		params:{
			id
		}
	})
}
