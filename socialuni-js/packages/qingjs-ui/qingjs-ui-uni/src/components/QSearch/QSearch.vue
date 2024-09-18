<template>
  <div class="row-col-center bd bd-round h30 font-sm">
    <slot></slot>
  </div>
</template>
<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

/*
显示出来已经选了的城市，给她画上钩
* */

@Component({})
class QSearch extends Vue {

}
export default toNative(QSearch)
</script>
