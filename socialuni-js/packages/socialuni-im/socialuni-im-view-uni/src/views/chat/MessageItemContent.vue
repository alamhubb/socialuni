<template>
  <div>
<!--    <span v-if="msg.status === 3"> 发送失败</span>-->
    <!--        <div class="bd-round size18 position-absolute mr-nn mt-nn right-0 row-all-center">
              <q-icon @click.stop="toIdentityAuth"
                      class="color-success"
                      size="16" icon="level"/>
            </div>-->
    <img v-if="msg.contentType === 102" mode="aspectFill"
           class="bd-radius bd"
           :style="{'max-width':'230px'}"
           :src="msg.contentData.sourcePicture.url"
           :show-menu-by-longpress="true"
           @click.stop="previewImage(msg.contentData.sourcePicture.url)"
    />

    <video v-else-if="msg.contentType === 104"  :src="msg.contentData.videoUrl" :style="{'max-width':'230px'}"
             controls></video>
    <div class="pd-xs bg-white bd-radius" v-else-if="msg.contentType !== 3"> {{ msg.content }}</div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator';
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";

@toNative
@Component({})
export default class MessageItemContent extends Vue {
  @Prop() msg: MessageVO

  previewImage(url) {
    uni.previewImage({
      current: 0,
      urls: [url]
    })
  }
}
</script>
