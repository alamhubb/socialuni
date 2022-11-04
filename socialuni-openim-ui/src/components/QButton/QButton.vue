<template>
    <button class="bg-green" @click="emitTest">ADD</button>
</template>

<script lang="ts">
import {Emit, Options, Vue} from "vue-property-decorator";

@Options({
})
export default class QButton extends Vue {

  @Emit('test')
  emitTest(){
  }
}

</script>
