<template>
  <q-button v-bind="$attrs"></q-button>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import QButton from "@/components/QButton/QButton.vue";

@Options({
  components: {
    QButton,
  }
})
export default class TestButton extends Vue {
}

</script>
