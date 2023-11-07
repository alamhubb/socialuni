import {request} from "@/network/index.js";


export function getHotSearch(){
	return request({
		url:`/search/hot/detail`
	})
}

export function getSearchSuggest(keywords){
	return request({
		url:`/search/suggest`,
		params:{
			keywords
		}
	})
}


export function getSearchResult(params){
	return request({
		url:`/cloudsearch`,
		params
	})
}