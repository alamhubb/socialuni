import {request} from "@/network/index.js";

//歌手分类列表
export function getSingerCategory(params){
	return request({
		url:'/artist/list',
		params
	})
}

//热门歌手
export function getSingerTop(){
	return request({
		url:'/top/artists'
	})
}

//歌手热门50首歌曲
export function getSingerSong(id){
	return request({
		url:'/artist/top/song',
		params:{
			id
		}
	})
}
//歌手专辑
export function getSingerAlbum(id,offset){
	return request({
		url:'/artist/album',
		params:{
			id,
			limit:10,
			offset
		}
	})
}

//歌手详情
export function getSingerDetail(id){
	return request({
		url:'/artist/detail',
		params:{
			id
		}
	})
}
//歌手描述
export function getSingerDesc(id){
	return request({
		url:'/artist/desc',
		params:{
			id
		}
	})
}

//相似歌手
export function getSimiSinger(id){
	return request({
		url:'/simi/artist',
		params:{
			id
		}
	})
}

//歌手MV
export function getSingerMV(id){
	return request({
		url:'/artist/mv',
		params:{
			id
		}
	})
}



