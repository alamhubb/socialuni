<template>
    <div class="h100p">
        <el-input v-model="uid">uid</el-input>
        <div class="mx-sm pb-60">
            <el-button @click="joinClick">join</el-button>
            <el-button @click="leaveClick">leave</el-button>
        </div>
        <div>
            <el-button @click="play">测试</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import SocialuniPeiwanAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import AgoraRTC from "agora-rtc-sdk-ng"
import openRequest from "@socialuni/socialuni-api-base/src/OpenRequest";

let rtc = {
    localAudioTrack: null,
    client: null
};

import heroicAdventureMp3 from "./assets/HeroicAdventure.mp3"
import test1 from "./assets/test1.mp3"

const appId = '5e681410a7434ce9bba3e268226ce537'
const streamIp = 'https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3'

const userId = '1a97b2fe76664ef68bfddf7256cf91d3'
const key = '1a97b2fe76664ef68bfddf7256cf91d3'
const secret = '999c0689cc794128b450c1d702f0e2f3'

const uidHeader = 'qqq'
const tokenHeader = '007eJxTYDi+2Ftdds/mXxN/+iw6/7xHWSjI6YDypDMbBSd0H7p/zr9FgcE01czC0MTQINHcxNgkOdUyKSnRONXIzMLIyCw51dTY/EXXv5SGQEYGm9KbTIwMTAyMQAjiMzMUFhYCAHfPIUU='

const client = AgoraRTC.createClient({
    mode: "rtc",
    codec: "vp8"
});

let config = {
    // Pass your App ID here.
    appId: "5e681410a7434ce9bba3e268226ce537",
    // Set the channel name.
    channel: "aaa",
    // Pass your temp token here.
    token: "007eJxTYPDqT36nIBq1M2cWV7TQl/oK/rWeDK2yayf+PZqfwXrHl0mBwTTVzMLQxNAg0dzE2CQ51TIpKdE41cjMwsjILDnV1Nj8XvO/lIZARoa67VcZGRkgEMRnZkhMTGRgAACRXh3F",
    // Set the user ID.
    uid: 123456
}

@Component({
    components: {SDialog, Plus}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }

    uid = 123456


    localAudioTrack = null
    client = null

    //https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3

    async joinClick() {
        // Join an RTC channel.
        await client.join(config.appId, config.channel, config.token, this.uid);
    }

    created() {
        //实现语音通话逻辑
        //参考以下步骤实现语音通话的逻辑：
        // 调用 createClient 方法创建 AgoraRTCClient 对象。
        // 调用 join 方法加入一个 RTC 频道，你需要在该方法中传入 App ID 、用户 ID、Token、频道名称。
        // 先调用 createMicrophoneAudioTrack 通过麦克风采集的音频创建本地音频轨道对象；然后调用 publish 方法，将本地音频轨道对象当作参数即可将音频发布到频道中。
        // 当一个远端用户加入频道并发布音频轨道时：
        // 监听 client.on("user-published") 事件。当 SDK 触发该事件时，在这个事件回调函数的参数中你可以获取远端用户 AgoraRTCRemoteUser 对象 。
        // 调用 subscribe 方法订阅远端用户 AgoraRTCRemoteUser 对象，获取远端用户的远端音频轨道 RemoteAudioTrack 对象。
        // 调用 play 方法播放远端音频轨道。
    }

    audioMixing = {
        state: "IDLE",
        // "IDLE" | "LOADING | "PLAYING" | "PAUSE"
        duration: 0
    }

    audioMixingTrack = null

    async play() {
        this.playBack()
    }

    async playBack() {
        const res = await AgoraRTC.getPlaybackDevices()
        console.log(res)
    }

    async playmp3(file) {
        console.log('触发了')
        if (this.audioMixing.state === "PLAYING" || this.audioMixing.state === "LOADING") return;
        const options = {}
        if (file) {
            // options.source = file;
            options.source = test1
        } else {
            options.source = test1
        }
        try {
            console.log(111111)
            this.audioMixing = {...this.audioMixing, state: "LOADING"}
            if (this.audioMixingTrack) {
                await client.unpublish(this.audioMixingTrack);
            }
            console.log(2222)
            console.log(options)
            // start audio mixing with local file or the preset file
            const track = await AgoraRTC.createBufferSourceAudioTrack(options);
            this.audioMixingTrack = track;
            track.play();
            console.log(3333)
            track.startProcessAudioBuffer({
                loop: true
            });
            this.audioMixing = {
                ...this.audioMixing,
                state: "PLAYING",
                duration: track.duration
            }
            console.log(4444)
        } catch (e) {
            console.error(e);
            // message.error(e.message)
            this.audioMixing = {...this.audioMixing, state: "IDLE"}
        }
    }

    async leaveClick() {
        // Leave the channel.
        await client.leave();
    }


    fasd(){
        //webkitURL is deprecated but nevertheless
        URL = window.URL || window.webkitURL;

        var gumStream; 						//stream from getUserMedia()
        var rec; 							//Recorder.js object
        var input; 							//MediaStreamAudioSourceNode we'll be recording

// shim for AudioContext when it's not avb.
        var AudioContext = window.AudioContext || window.webkitAudioContext;
        var audioContext //audio context to help us record

        var recordButton = document.getElementById("recordButton");
        var stopButton = document.getElementById("stopButton");
        var pauseButton = document.getElementById("pauseButton");

//add events to those 2 buttons
        recordButton.addEventListener("click", startRecording);
        stopButton.addEventListener("click", stopRecording);
        pauseButton.addEventListener("click", pauseRecording);

        function startRecording() {
            console.log("recordButton clicked");

            /*
              Simple constraints object, for more advanced audio features see
              https://addpipe.com/blog/audio-constraints-getusermedia/
            */

            var constraints = { audio: true, video:false }

            /*
               Disable the record button until we get a success or fail from getUserMedia()
           */

            recordButton.disabled = true;
            stopButton.disabled = false;
            pauseButton.disabled = false

            /*
                We're using the standard promise based getUserMedia()
                https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
            */

            navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
                console.log("getUserMedia() success, stream created, initializing Recorder.js ...");

                /*
                  create an audio context after getUserMedia is called
                  sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
                  the sampleRate defaults to the one set in your OS for your playback device

                */
                audioContext = new AudioContext();

                //update the format
                document.getElementById("formats").innerHTML="Format: 1 channel pcm @ "+audioContext.sampleRate/1000+"kHz"

                /*  assign to gumStream for later use  */
                gumStream = stream;

                /* use the stream */
                input = audioContext.createMediaStreamSource(stream);

                /*
                  Create the Recorder object and configure to record mono sound (1 channel)
                  Recording 2 channels  will double the file size
                */
                rec = new Recorder(input,{numChannels:1})

                //start the recording process
                rec.record()

                console.log("Recording started");

            }).catch(function(err) {
                //enable the record button if getUserMedia() fails
                recordButton.disabled = false;
                stopButton.disabled = true;
                pauseButton.disabled = true
            });
        }

        function pauseRecording(){
            console.log("pauseButton clicked rec.recording=",rec.recording );
            if (rec.recording){
                //pause
                rec.stop();
                pauseButton.innerHTML="Resume";
            }else{
                //resume
                rec.record()
                pauseButton.innerHTML="Pause";

            }
        }

        function stopRecording() {
            console.log("stopButton clicked");

            //disable the stop button, enable the record too allow for new recordings
            stopButton.disabled = true;
            recordButton.disabled = false;
            pauseButton.disabled = true;

            //reset button just in case the recording is stopped while paused
            pauseButton.innerHTML="Pause";

            //tell the recorder to stop the recording
            rec.stop();

            //stop microphone access
            gumStream.getAudioTracks()[0].stop();

            //create the wav blob and pass it on to createDownloadLink
            rec.exportWAV(createDownloadLink);
        }

        function createDownloadLink(blob) {

            var url = URL.createObjectURL(blob);
            var au = document.createElement('audio');
            var li = document.createElement('li');
            var link = document.createElement('a');

            //name of .wav file to use during upload and download (without extendion)
            var filename = new Date().toISOString();

            //add controls to the <audio> element
            au.controls = true;
            au.src = url;

            //save to disk link
            link.href = url;
            link.download = filename+".wav"; //download forces the browser to donwload the file using the  filename
            link.innerHTML = "Save to disk";

            //add the new audio element to li
            li.appendChild(au);

            //add the filename to the li
            li.appendChild(document.createTextNode(filename+".wav "))

            //add the save to disk link to li
            li.appendChild(link);

            //upload link
            var upload = document.createElement('a');
            upload.href="#";
            upload.innerHTML = "Upload";
            upload.addEventListener("click", function(event){
                var xhr=new XMLHttpRequest();
                xhr.onload=function(e) {
                    if(this.readyState === 4) {
                        console.log("Server returned: ",e.target.responseText);
                    }
                };
                var fd=new FormData();
                fd.append("audio_data",blob, filename);
                xhr.open("POST","upload.php",true);
                xhr.send(fd);
            })
            li.appendChild(document.createTextNode (" "))//add a space in between
            li.appendChild(upload)//add the upload link to li

            //add the li element to the ol
            recordingsList.appendChild(li);
        }
    }
}
</script>
