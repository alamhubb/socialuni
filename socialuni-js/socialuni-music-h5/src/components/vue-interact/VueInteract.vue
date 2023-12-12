<template>
  <div ref="interactContainer">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator';
import interact from "interactjs";

@Component({
  components: {}
})
export default class VueInteract extends Vue {
  $refs:{
    interactContainer: HTMLDivElement
  }

  mounted(){
    console.log(this.$refs.interactContainer)
    const position = { x: 0, y: 0 }
    interact(this.$refs.interactContainer).draggable({
      listeners: {
        start (event) {
          console.log(event.type, event.target)
        },
        move (event) {
          position.x += event.dx
          position.y += event.dy

          event.target.style.transform =
              `translate(${position.x}px, ${position.y}px)`
        },
      }
    })
  }
}
</script>
