<template>
  <view v-if="dialogVisible" class="q-popup-box">
    <q-model @click="close" v-if="modal">
      <!--        ios端不支持prevent需要用stop代替-->
    </q-model>
    <div class="h100p" :class="bottom?'col-end':'row-all-center'">
      <view class="q-popup" :class="bottom?'w100vw bt-radius-20':'bd-radius-20'"
            @touchmove.stop.prevent
            @click.stop
      >
        <slot></slot>
      </view>
    </div>
  </view>
</template>
<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import QModel from '@/qing-ui/components/QModel/QModel.vue'

/*
显示出来已经选了的城市，给她画上钩
* */
@Component({
  components: { QModel }
})
export default class QPopup extends Vue {
  //如果是头顶或者底部则不为width100
  @Prop({ default: '' }) readonly position: string
  @Prop({ default: false }) readonly bottom: boolean
  @Prop({ default: false }) readonly top: boolean
  @Prop({ default: true }) readonly modal: boolean

  dialogVisible: boolean = false

  open () {
    this.dialogVisible = true
  }


  @Emit()
  close () {
    this.dialogVisible = false
  }
  //下的话宽度100，中的话宽度90
  //下的话col-end 中的话 row-all-center
}
</script>
