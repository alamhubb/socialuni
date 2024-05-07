<template>
    <div>load app module</div>
</template>

<script lang="ts">
import {Emit, Component, Vue} from 'vue-facing-decorator'

@toNative
@Component({
    components: {}
})
export default class SocialuniAppLoad extends Vue {
}


</script>
