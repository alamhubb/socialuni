import MusicPlayerSongInfoRO from "@/components/MusicPlayerSongInfoRO.ts";

export default class MusicPlayerSongPlayingInfoRO extends MusicPlayerSongInfoRO {
    //开始播放时间
    playingTimestamp: Date = null
    //单位秒
    playingTime: number
    playing: boolean
}