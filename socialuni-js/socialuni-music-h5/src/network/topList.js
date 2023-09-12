import {request} from "@/network/index.js";

export function getTopList(){
	return request({
		url:'/toplist/detail'
	})
}


export function getMM(){
	return request({
		url:``
	})
}


export function createSongList(name,privacy){
	return request({
		url:`/playlist/create`,
		params:{
			name,
			privacy
		}
	})
}


export function detailSongList(id){
	return request({
		url:`/playlist/delete`,
		params:{
			id,
			timestamp:Date.now()
		}
	})
}


export function upload(id,data){
	return request({
		url:`/playlist/cover/update`,
		method:'POST',
		headers: {
			'Content-Type': 'multipart/form-data',
		},
		data:{
			id,
			...data
		}
	})
}
