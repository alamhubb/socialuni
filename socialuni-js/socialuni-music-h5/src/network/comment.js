import {request} from "@/network/index.js";


//评论
export function getSongListComment(params){
	return request({
		url:'/comment/hot',
		params
	})
}

export function getComment(params){
	return request({
		url:`/comment/playlist`,
		params
	})
}

export function getSendComment(params){
	return request({
		url:`/comment`,
		params
	})
}

export function getAlbumContent(id){
	return request({
		url:`/album?id=${id}`
	})
}