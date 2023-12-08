import MusicPlayerSongInfoRO from "./MusicPlayerSongInfoRO";

export default class MusicPlayerSongPlayingInfoRO extends MusicPlayerSongInfoRO {
    //开始播放时间
    playingTimestamp: Date = null
    //单位秒
    playingTime: number
    playing: boolean

    constructor(musicPlayerSongInfoRO?: MusicPlayerSongInfoRO) {
        super(musicPlayerSongInfoRO);
        this.playingTimestamp = new Date();
        this.playingTime = 0;
        this.playing = false;
    }
}
