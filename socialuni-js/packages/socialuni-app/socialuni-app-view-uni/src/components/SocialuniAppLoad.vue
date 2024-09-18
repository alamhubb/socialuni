<template>
    <div>load app module</div>
</template>

<script lang="ts">
import {Emit, Component, Vue, toNative} from 'vue-facing-decorator'


@Component({
    components: {}
})
class SocialuniAppLoad extends Vue {
}
export default toNative(SocialuniAppLoad)
</script>
