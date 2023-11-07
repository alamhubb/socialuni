export const routes = [
	{path: '/',redirect:'/findMusic'},
	{path: '/search',component:() => import('@/views/Search/Search.vue'),
		children: [
			{path: '',component:() => import('@/views/Search/pages/song.vue')},
			{path: 'singer',component:() => import('@/views/Search/pages/singer.vue')},
			{path: 'album',component:() => import('@/views/Search/pages/album.vue')},
			{path: 'video',component:() => import('@/views/Search/pages/video.vue')},
			{path: 'songMenu',component:() => import('@/views/Search/pages/songMenu.vue')},
		]
	},
	{path: '/unique',component:() => import('@/views/Aside/findMusic/pages/unique.vue')},
	{
		path:'/findMusic',component:() => import('@/views/Aside/findMusic/findMusic.vue'),
		children:[
			{path:'',component:() => import('@/views/Aside/findMusic/pages/recommend.vue')},
			{path:'exclusive',component:() => import('@/views/Aside/findMusic/pages/exclusive.vue')},
			{path:'songMenu',component:() => import('@/views/Aside/findMusic/pages/songMenu.vue')},
			{path:'topList',component:() => import('@/views/Aside/findMusic/pages/topList.vue')},
			{path:'singer',component:() => import('@/views/Aside/findMusic/pages/singer.vue')},
			{path:'newMusic',component:() => import('@/views/Aside/findMusic/pages/newMusic.vue'),
				children:[
					{path:'',component:() => import('@/views/Aside/findMusic/pages/newMusic/newSong.vue')},
					{path:'newAlbum',component:() => import('@/views/Aside/findMusic/pages/newMusic/newAlbum.vue')}
				]
			}
		]
	},
	{path:'/podcast',component:() => import('@/views/Aside/podcast/podcast.vue'),
		children: [
			{path: '',component:() => import('@/views/Aside/podcast/pages/podcastContent.vue')},
			{path: 'programTop',component:() => import('@/views/Aside/podcast/pages/programTop.vue')},
			{path: 'newComer',component:() => import('@/views/Aside/podcast/pages/newComer.vue')},
			{path: 'hotTopList',component:() => import('@/views/Aside/podcast/pages/hotTopList.vue')},
			{path: 'hoursTopList',component:() => import('@/views/Aside/podcast/pages/hoursTopList.vue')}
		]
	},
	{path: '/program',component:() => import('@/views/RadioDetailed/program.vue')},
	{path: '/categoryList',component:() => import('@/views/RadioDetailed/categoryList.vue')},
	{path:'/video',component:() => import('@/views/Aside/video/video.vue'),
		children: [
			{path: 'video',component:() => import('@/views/Aside/video/pages/videoContent.vue')},
			{path: '',component:() => import('@/views/Aside/video/pages/MV.vue')},
			{path: 'allMV',component:() => import('@/views/Aside/video/pages/allMV.vue')},
			{path: 'mvTopList',component:() => import('@/views/Aside/video/pages/mvTopList.vue')},
		]
	},
	{path:'/songDetail',component:() => import('@/views/SongDetail/songDetail.vue'),
		children: [
			{path: '',component:() => import('@/views/SongDetail/pages/SongList.vue')},
			{path: 'comment',component: () => import('@/views/SongDetail/pages/Comment.vue')},
			{path: 'collect',component: () => import('@/views/SongDetail/pages/Collect.vue')}
		]
	},
	{ path: '/SingerContent', component:() => import('@/views/SingerContent/SingerContent.vue'),
		children: [
			{path: '',component:() => import('@/views/SingerContent/pages/singerMusic.vue')},
			{path: 'album',component:() => import('@/views/SingerContent/pages/album.vue')},
			{path: 'mv',component:() => import('@/views/SingerContent/pages/MV.vue')},
			{path: 'singerDetail',name:'singerDetail',component:() => import('@/views/SingerContent/pages/singerDetail.vue')},
			{path: 'similaritySinger',component:() => import('@/views/SingerContent/pages/similaritySinger.vue')}
		]
	},
	{path: '/friend',component:() => import('@/views/Aside/friend/friend.vue')},
	{path: '/FM',component:() => import('@/views/Aside/Fm/FM.vue')},
	{path: '/RecentPlay',component:() => import('@/views/Aside/RecentPlay/RecentPlay.vue')},
	{path: '/myPodcast',component:() => import('@/views/Aside/myPodcast/myPodcast.vue')},
	{path: '/myCollect',component:() => import('@/views/Aside/myCollect/MyCollect.vue'),
		children: [
			{path: '',component:() => import('@/views/Aside/myCollect/pages/album.vue')},
			{path: 'singer',component:() => import('@/views/Aside/myCollect/pages/singer.vue')},
			{path: 'video',component:() => import('@/views/Aside/myCollect/pages/video.vue')},
			{path: 'professional',component:() => import('@/views/Aside/myCollect/pages/professional.vue')},
		]
	},
	{path: '/likeMusic',component:() => import('@/views/Aside/likeMusic/likeMusic.vue')},
	{path: '/mySongList',component:() => import('@/views/Aside/mySongList/mySongList.vue')},
	{path: '/VideoDetail',component:() => import('@/views/VideoDetail/VideoDetail.vue')},
]
