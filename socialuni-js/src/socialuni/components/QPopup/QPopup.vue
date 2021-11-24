<template>
    <view v-if="value" class="q-popup-box">
        <q-model @click="input(false)" v-if="modal">
            <!--        ios端不支持prevent需要用stop代替-->
        </q-model>
        <view class="h100p flex-row">
            <view class="q-popup" :class="popupClass"
                  @touchmove.stop.prevent
                  @click.stop
            >
                <slot></slot>
            </view>
        </view>
    </view>
</template>
<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import QModel from '@/socialuni/components/QModel/QModel.vue'

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
    @Model('input') readonly value: boolean




    @Emit()
    input (value: boolean) {
      return value
    }

    get popupClass () {
      return this.top ? 'top' : this.bottom ? 'bottom' : this.position
    }
}
</script>
