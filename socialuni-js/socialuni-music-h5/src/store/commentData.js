import {getSongListComment} from '@/network/comment.js'



const commentData = {
	state:{
		commentArray:[],
		count:0
	},
	mutations:{
		setCommentData(state,payload){
			state.commentData = payload
		},
		setCount(stage,count){
			stage.count = count
		}
	},
	actions:{
		async getComment({commit},payload){
			let res = await getSongListComment(payload)
			commit('setCommentData',res.data.hotComments)
		}
	}
}

export default commentData