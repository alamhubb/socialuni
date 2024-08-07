export default class MusicPlayerSongInfoRO {
    songId: number = null
    name: string = null
    albumImg: string = null
    author: string[] = null
    album: string = null
    musicTime: number = null
    musicUrl: String = null


    constructor(musicPlayerSongInfoRO?: MusicPlayerSongInfoRO) {
        if (musicPlayerSongInfoRO) {
            this.songId = musicPlayerSongInfoRO.songId;
            this.name = musicPlayerSongInfoRO.name;
            this.author = musicPlayerSongInfoRO.author;
            this.album = musicPlayerSongInfoRO.album;
            this.musicTime = musicPlayerSongInfoRO.musicTime;
            this.albumImg = musicPlayerSongInfoRO.albumImg;
            this.musicUrl = musicPlayerSongInfoRO.musicUrl;
        }
    }
}
