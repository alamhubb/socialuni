import song from '../assets/song/song.json'
import {getSongDetail} from "@/network/song.js";
import {getSongArray} from '@/network/recommend.js'
import {getRecommendSongs} from "@/network/recommend.js";
import {getSongListDetailDynamic} from '@/network/songList.js'

const songDetail = {
	state:{
		songDetail:song,
		songArray:[],//歌单列表
		value:0,
		play:undefined,
		is:false,
		songList:{boolean:false}, //歌单头部动态,
		commentID:'',//歌单id,
		commentData:null,
		keywordList:['起风了','海阔天空'],
		keywords:''
	},
	mutations:{
		setHeader(state){
			state.songList.boolean = false
		},
		setSongList(state,payload){
			state.songList = payload
		},
		//历史记录
		setKeyword(state,keyword){
			state.keywords = keyword
			let currentIndex = state.keywordList.findIndex(item => item === keyword)
			if (currentIndex !== -1){
				state.keywordList.splice(currentIndex,1)
			}else if (state.keywordList.length > 7){
				state.keywordList.splice(state.keywordList.length-1,1)
			}
			state.keywordList.unshift(keyword)
		},
		del(state,index){
			state.keywordList.splice(index,1)
		},
		setSongDetail(stata,payload){
			stata.songDetail = payload
		},
		//歌单列表
		setSongArray(state,payload){
			state.is = false
			state.songList = {
				...payload,
				boolean:true
			}
			payload.tracks.forEach((item,index) => item.index = index + 1)
			state.songArray = payload.tracks
		},
		//设置歌手单曲
		setSongMusic(state,payload){
			state.is = false
			payload?.forEach((item,index) => item.index = index + 1)
			state.songArray = payload
		},
		//每日歌曲推荐
		setEverySong(state,payload){
			state.is = true
			payload.forEach((item,index) => item.index = index + 1)
			state.songArray = payload
		},
		//切换歌曲
		change(state,value){
			state.value += value
			if (state.value < 0){
				state.value = state.songArray.length -1
			}else if (state.value > state.songArray.length -1){
				state.value = 0
			}
			state.songDetail = state.songArray[state.value]
		},
		//双击播放
		play(state,index){
			state.value = index
			state.play ++
		},
		setIs(state,boolean){
			state.is = boolean
		},
		//设置歌单id
		setID(state,id){
			state.commentID = id
		},
		//歌单动态
		setDynamic(state,data){
			state.commentData = data
		}
	},
	actions:{
		//歌曲id获取歌曲详情
		getSongDetailData(context,id){
			return new Promise(resolve => {
				getSongDetail(id).then(res =>{
					context.commit('setSongDetail',res.data.songs[0])
					resolve(true)
				})
			})
		},
		//每日推荐歌曲
		getEverySong(context){
			context.commit('setIs',true)
			getRecommendSongs().then(res => {
				context.commit('setEverySong',res.data.data.dailySongs)
			})
		},
		//歌单列表
		async getSongList(context,id){
			context.commit('setID',id)
			context.commit('setHeader')
			getSongListDetailDynamic(id).then(res => {
				context.commit('setDynamic',res.data)
			})
			let res = await getSongArray(id)
			context.commit('setSongArray',res.data.playlist)
			new Promise(resolve => {
				resolve()
			})
		}
	},
	getters:{

	}
}

export default songDetail
