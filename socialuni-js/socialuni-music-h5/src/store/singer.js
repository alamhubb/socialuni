const singer = {
	state:{
		singerId:'',
		likeMusic:[]
	},
	mutations:{
		setSingerId(state,id){
			state.singerId = id
		},
		setLikeMusic(state,payload){
			state.likeMusic = payload
		}
	},
	actions:{
	
	}
}

export default singer
