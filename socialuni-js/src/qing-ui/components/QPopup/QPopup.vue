<template>
  <div v-if="dialogVisible || value"
       class="position-fixed overflow-hidden index-999 h100vh w100vw top-0 bottom-0 left-0 right-0">
    <q-model @click="cancel" v-if="modal">
      <!--        ios端不支持prevent需要用stop代替-->
    </q-model>
    <div class="h100p" :class="bottom?'col-end':'row-all-center'">
      <div class="q-popup bg-white position-absolute index-1005 flex-col"
           :class="bottom?'w100vw bt-radius-20':'w90vw bd-radius-20'"
           @touchmove.stop.prevent
           @click.stop
      >
        <div class="row-between-center bb-1 py-smm pd-xs">
          <div class="flex-row flex-1">
            <slot name="headerLeft"></slot>
          </div>
          <div class="flex-row flex-none px-sm">
            <q-button v-if="!hideConfirm" @click="confirm" theme text>{{ confirmText }}</q-button>
            <q-button class="ml-sm" v-if="!hideCancel" @click="cancel" text info>{{ cancelText }}</q-button>
          </div>
        </div>
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import QModel from '@/qing-ui/components/QModel/QModel.vue'
import QButton from '@/qing-ui/components/QButton/QButton.vue'

/*
显示出来已经选了的城市，给她画上钩
* */
@Component({
  components: {
    QButton,
    QModel
  }
})
export default class QPopup extends Vue {
  //如果是头顶或者底部则不为width100
  @Prop({ default: '' }) readonly position: string
  @Prop({ default: false }) readonly bottom: boolean
  @Prop({ default: false }) readonly top: boolean
  @Prop({ default: true }) readonly modal: boolean
  @Prop({ default: false }) readonly hideModal: boolean
  @Prop({ default: false }) readonly hideConfirm: boolean
  @Prop({ default: false }) readonly hideCancel: boolean
  @Prop({ default: '关 闭' }) readonly cancelText: boolean
  @Prop({ default: '确 定' }) readonly confirmText: boolean

  @Model() value: boolean

  dialogVisible: boolean = false

  open () {
    this.dialogVisible = true
  }

  close () {
    this.input(false)
    this.dialogVisible = false
  }


  @Emit()
  cancel () {
    this.close()
  }

  @Emit()
  confirm () {
    this.close()
  }

  @Emit()
  input (visible) {
    return visible
  }

  //下的话宽度100，中的话宽度90
  //下的话col-end 中的话 row-all-center
}
</script>
