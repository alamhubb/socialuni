import {request} from "@/network/index.js";

//获取视频所有标签列表
export function getVideoAllTag(){
	return request({
		url:`/video/group/list`
	})
}

//获取视频热门标签分类
export function getVideoTags(){
	return request({
		url:`/video/category/list`
	})
}

export function getAllVideo(offset){
	return request({
		url:`/video/timeline/all`,
		params:{
			offset
		}
	})
}

export function getVideoUrl(id){
	return request({
		url:`/video/url?id=${id}`
	})
}
//获取视频详情
export function getVideoDetail(id){
	return request({
		url:`/video/detail?id=${id}`
	})
}

//获取推荐视频
export function getRecommendVideo(){
	return request({
		url:`/video/timeline/recommend`
	})
}

//视频标签下分类视频
export function getCategoryVideo(params){
	return request({
		url:`/video/group`,
		params
	})
}

//获取最新MV
export function getNewMv(area){
	return request({
		url:`/mv/first`,
		params:{
			limit:8,
			area
		}
	})
}

//热播MV
export function getHotMv(){
	return request({
		url:`/personalized/mv`
	})
}

//网易出品
export function getFooterMv(){
	return request({
		url:`/mv/exclusive/rcmd`,
		params:{
			limit:8
		}
	})
}

//MV排行
export function getMvTopList(area,offset){
	return request({
		url:`/top/mv`,
		params:{
			limit:10,
			area,
			offset
		}
	})
}

//mv详情
export function getMvDetail(id){
	return request({
		url:`/mv/detail?mvid=${id}`
	})
}

//获取Mv的Url
export function getMvUrl(id){
	return request({
		url:`/mv/url?id=${id}`
	})
}

//相似MV
export function getSimilarMv(id){
	return request({
		url:`/simi/mv?mvid=${id}`
	})
}

//相关视频
export function getSimilarVideo(id){
	return request({
		url:`/related/allvideo?id=${id}`
	})
}

//mv评论
export function getMvComment(id,offset){
	return request({
		url:`/comment/mv`,
		params:{
			id,
			limit:10,
			offset
		}
	})
}

//视频评论
export function getVideoComment(id,offset){
	return request({
		url:`/comment/video`,
		params:{
			id,
			limit:10,
			offset
		}
	})
}

//全部mv
export function getAllMv(params){
	return request({
		url:`/mv/all`,
		params
	})
}