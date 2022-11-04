const innerAudioContext = uni.createInnerAudioContext();
let sourceUrl = "";
let isPlay = false;
export function playAudio(src) {
  if (!src && isPlay) {
    innerAudioContext.stop();
  } else if (src === sourceUrl) {
    if (isPlay) {
      innerAudioContext.stop();
    } else {
      innerAudioContext.play();
    }
  } else {
    sourceUrl = src;
    if (isPlay) {
      innerAudioContext.stop();
    }
    innerAudioContext.src = sourceUrl;
    innerAudioContext.play();
  }
}
innerAudioContext.onPlay(() => {
  isPlay = true;
});
innerAudioContext.onStop(() => {
  isPlay = false;
});
