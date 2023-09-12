export function formatData(array){
	return array.map(item => {
		return{
			al:{picUrl:item.program.coverUrl},
			id:item.program.mainSong.id,
			name:item.program.name,
			dt:item.program.duration,
			long:item.score,
			home:array[0].score,
			label:item.program.dj.brand,
			album:item.program.radio.category
		}
	})
}


export function formatNewData(array){
	return array.map(item => {
		return{
			al:{picUrl:item.picUrl},
			id:item.id,
			name:item.name,
			long:item.score,
			home:array[0].score,
			label:item.creatorName,
			album:item.category
		}
	})
}

export function formatMvData(array){
	return array.map(item => {
		return{
			data:{
				coverUrl:item['cover'] || item['picUrl'],
				vid:item.id,
				cover:item.playCount,
				title:item.name,
				creator:{
					nickname:item.artists.map(item => item.name).join(' / ')
				}
			}
		}
	})
}


export function formatMvInfo(res){
	return {
		photo:res.data.data.artists[0].img1v1Url,
		person:res.data.data.artistName,
		id:res.data.data.id,
		name:res.data.data.name,
		title:res.data.data.name,
		count:res.data.data.playCount,
		time:res.data.data.publishTime,
		shareCount:res.data.data.shareCount,
		subCount:res.data.data.subCount,
		desc:res.data.data.desc
	}
}

import {formatTime} from "@/utlis/formatTime.js";


export function formatVideoInfo(res){
	return {
		photo:res.data.data.creator.avatarUrl,
		person:res.data.data.creator.nickname,
		id:res.data.data.vid,
		name:res.data.data.title.slice(0,20),
		title:res.data.data.description,
		count:res.data.data.playTime,
		time:formatTime(res.data.data.publishTime),
		shareCount:res.data.data.shareCount,
		subCount:res.data.data.subscribeCount,
		desc:res.data.data.description
	}
}



export function formatVideoData(array){
	return array.map(item => {
		return{
			data:{
				coverUrl:item.coverUrl,
				vid:item.vid,
				durationms:item.durationms,
				cover:item.playTime,
				title:item.title,
				creator:{
					nickname:item.creator.map(item => item.userName).join(' / ')
				}
			}
		}
	})
}


export function formatSongMenuData(array){
	return array.map((item,index) => {
		return{
			accountId:index,
			id:item.id,
			picUrl:item.coverImgUrl,
			name:item.name,
			num:item.trackCount,
			label:item.creator.nickname
		}
	})
}


export function formatLyric(lyric){
	 return lyric.split(/\n/igs).map(item => {
		let fen = item.slice(1,3)
		let miao = item.slice(4,6)
		let haoMiao = item.slice(7,10)
		return {
			fen,miao,haoMiao,
			lyric:item.slice(10,item.length).replace(/]/g,''),
			item,
			time:parseInt(haoMiao) + parseInt(miao) * 1000 + parseInt(fen) *60 *1000
		}
	})
}

export function formatAlbum(data){
	return{
		id:1,
		coverImgUrl:data.blurPicUrl,
		name:data.name,
		trackCount:data.mark,
		playCount:data.id,
		creator:{
			avatarUrl:data.picUrl
		},
		tags:data.artists.map(item => item.name),
		description:data.description,
		createTime:data.publishTime,
		boolean:true
	}
}

export function formatNewMusic(data){
	return data.map(item => {
			return{
				al:{id:item.id,picUrl:item.album.picUrl},
				id:item.id,
				name:item.name,
				dt:item.duration,
				label:item.album.artists[0].name,
				album:item.album.name
			}
		})
}
