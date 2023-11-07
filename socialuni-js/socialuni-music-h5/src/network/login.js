import {request} from "@/network/index.js";

//获取验证码
export function getPhoneCode(phone){
	return request({
		url:"/captcha/sent",
		params:{
			phone
		}
	})
}

export function getLoginStatus(data){
	return request({
		url:`/login/cellphone`,
		method:'POST',
		data
	})
}

export function loginAny(){
	return request({
		url:'/register/anonimous',
		method:'get'
	})
}

export function phoneLogin(params){
	return request({
		url:'/login/cellphone',
		method:'get',
		params
	})
}


export function getKey(){
	return request({
		url:'/login/qr/key',
		params:{
			timestamp:Date.now()
		}
	})
}


export function createLogin(key){
	return request({
		url:'/login/qr/create',
		params:{
			key,
			timestamp:Date.now()
		}
	})
}


export function checkLogin(key){
	return request({
		url:`/login/qr/check`,
		params:{
			key,
			timestamp:Date.now()
		}
	})
}

export function getInfo(){
	return request({
		url:`/user/account`
	})
}

export function getLogout(){
	return request({
		url:`/logout`
	})
}
