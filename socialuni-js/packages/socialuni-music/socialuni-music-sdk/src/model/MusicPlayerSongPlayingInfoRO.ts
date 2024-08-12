import MusicPlayerSongInfoRO from "./MusicPlayerSongInfoRO";

//音乐信息播放状态，比列表信息多播放信息
export default class MusicPlayerSongPlayingInfoRO extends MusicPlayerSongInfoRO {
    //开始播放时间
    playingTimestamp: Date = null
    //单位秒
    playingTime: number
    playing: boolean

    constructor(musicPlayerSongInfoRO?: MusicPlayerSongPlayingInfoRO) {
        super(musicPlayerSongInfoRO);
        if (musicPlayerSongInfoRO) {
            if (musicPlayerSongInfoRO.playingTimestamp) {
                this.playingTimestamp = musicPlayerSongInfoRO.playingTimestamp;
                this.playingTime = musicPlayerSongInfoRO.playingTime;
                this.playing = musicPlayerSongInfoRO.playing;
            } else {
                this.playingTimestamp = new Date();
                this.playingTime = 0;
                this.playing = false;
            }
        }
    }
}
