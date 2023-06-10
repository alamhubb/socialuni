<template>
  <div class="row-col-center bd bd-radius h30 px-sm">
    <input :value="value" class="flex-1 px-xs font-df" @input="inputEvent" :placeholder="placeholder"/>
    <q-icon v-show="(!hideClear)&&value&&value.length" icon="close" class="color-sub pd-xs" size="12"
            @click="clear"></q-icon>
  </div>
</template>

<script lang="ts">
import {Options, Emit, Model, Prop, Vue} from 'vue-property-decorator'
import QIcon from 'socialuni-ui-uni/src/components/QIcon/QIcon.vue'

@Options({
  components: {QIcon}
})
export default class QInput extends Vue {
  @Model('modelValue') readonly value: string

  @Prop({default: false, type: Boolean}) readonly hideClear: boolean
  @Prop({default: ''}) readonly placeholder: string

  inputEvent({detail}) {
    this.input(detail.value)
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
