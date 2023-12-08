export default class MusicPlayerSongInfoRO {
    songId: number = null
    title: string = null
    author: string[] = null
    album: string = null
    musicTime: number = null
    musicUrl: String = null


    constructor(musicPlayerSongInfoRO?: MusicPlayerSongInfoRO) {
        if (musicPlayerSongInfoRO) {
            this.songId = musicPlayerSongInfoRO.songId;
            this.title = musicPlayerSongInfoRO.title;
            this.author = musicPlayerSongInfoRO.author;
            this.album = musicPlayerSongInfoRO.album;
            this.musicTime = musicPlayerSongInfoRO.musicTime;
            this.musicUrl = musicPlayerSongInfoRO.musicUrl;
        }
    }
}
