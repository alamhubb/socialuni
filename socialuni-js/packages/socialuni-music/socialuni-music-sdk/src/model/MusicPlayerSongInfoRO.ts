//音乐信息基础，列表信息类
export default class MusicPlayerSongInfoRO {
    no?: number = null
    songId: string = null
    musicId?: number = null
    musicName: string = null
    //毫秒
    musicTime: number = null
    author: string = null

    albumImg: string = null
    album: string = null
    musicUrl: String = null


    constructor(musicPlayerSongInfoRO?: MusicPlayerSongInfoRO) {
        if (musicPlayerSongInfoRO) {
            this.no = musicPlayerSongInfoRO.no;
            this.songId = musicPlayerSongInfoRO.songId;
            this.musicId = musicPlayerSongInfoRO.musicId;
            this.musicName = musicPlayerSongInfoRO.musicName;
            this.musicTime = musicPlayerSongInfoRO.musicTime;
            this.author = musicPlayerSongInfoRO.author;
            this.album = musicPlayerSongInfoRO.album;
            this.albumImg = musicPlayerSongInfoRO.albumImg;
            this.musicUrl = musicPlayerSongInfoRO.musicUrl;
        }
    }
}
