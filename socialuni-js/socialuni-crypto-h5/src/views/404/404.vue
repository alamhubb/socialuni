<template>
    <div class="h100p row-center">
        <div class="mt-20vh font-32 font-bold">不存在的资源</div>
    </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'


@Component({})
class Page404 extends Vue {
}
export default toNative(Page404)
</script>
