<template>
  <div class="row-col-center bd bd-radius h30 px-sm bg-white">
    <div class="flex-1 overflow-hidden">
      <input :value="value" class="bd-none w100p font-df overflow-hidden" @input="inputEvent" :placeholder="placeholder"/>
    </div>
    <q-icon v-show="(!hideClear)&&value&&value.length" icon="close" class="color-sub pd-xs flex-none" size="12"
            @click="clear"></q-icon>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, toNative} from 'vue-facing-decorator'
import QIcon from "./QIcon.vue";

@toNative
@Component({
  components: {QIcon}
})
export default class QInput extends Vue {
  @Model('modelValue') readonly value: string

  @Prop({default: false, type: Boolean}) readonly hideClear: boolean
  @Prop({default: ''}) readonly placeholder: string

  inputEvent(e) {
    if (e.detail && e.detail.value) {
      this.input(e)
    } else {
      this.input(e.target.value)
    }
  }

  @Emit('update:modelValue')
  input(value) {
    return value
  }

  @Emit()
  clear() {
    this.input('')
  }
}
</script>
