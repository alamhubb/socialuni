import {phoneLogin} from "@/network/login.js";
import { ElMessage } from 'element-plus'
import {loginAny} from '@/network/login.js'
const login = {
	state:{
		profile:null,
	},
	mutations:{
		setUser(state,payload){
			state.profile = payload
		}
	},
	actions:{
		async login(context,payload){
			let res = await phoneLogin(payload)
			console.log (res)
			if (res?.data.code === 200){
				context.commit('setUser',res.data.profile)
				ElMessage.success({
					message: '登陆成功',
					type: 'success',
				})
				return new Promise(resolve => {
					resolve(true)
				})
			}else {
				ElMessage.error({
					message: '登陆失败',
					type: 'success',
				})
				loginAny().then(res => {
					console.log('',res)
					context.commit('setUser',{...res.data,nickname:'游客登录',avatarUrl:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.700mh.com%2Fskin%2Fdh%2F8.jpg&refer=http%3A%2F%2Fwww.700mh.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1666174557&t=882276397f1c43ffb674fb697b073a74'})
				})
			}
		}
	}
}

export default login
