<template>
  <input class="row-col-center bd bd-round h30 px-sm" :value="value" @input="inputEvent"/>
</template>

<script lang="ts">
import { Component, Emit, Model, Vue } from 'vue-property-decorator'

@Component
export default class QInput extends Vue {
  @Model('input') readonly value: string

  inputEvent ({ detail }) {
    console.log(detail)
    this.input(detail.value)
  }

  @Emit()
  input (value) {
    console.log(this.value)
    console.log(value)
    return value
  }
}
</script>
