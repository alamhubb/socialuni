import {request} from "@/network/index";

//歌曲详情
export function getSongDetail(id){
	return request({
		url:`/song/detail?ids=${id}`
	})
}

export function isPlay(id){
	return request({
		url:`/check/music?id=${id}`
	})
}

//最新音乐
export function getNewMusic(type){
	return request({
		url:`/top/song`,
		params:{
			type
		}
	})
}
//全部新碟
export function getNewAlbum(params){
	return request({
		url:`/album/new`,
		params
	})
}
//获取喜欢音乐
export function getLikeMusic(id){
	return request({
		url:'/likelist',
		params:{
			uid:id
		}
	})
}

//获取音乐url
export function getUrl(id){
	return request({
		url:'/song/url',
		params:{
			id
		}
	})
}

//获取歌词
export function getLyric(id){
	return request({
		url:`/lyric?id=${id}`
	})
}


