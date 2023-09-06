<template>
    <div class="h100p">
        123
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'


@Component({
    components: {SDialog, Plus}
})
export default class PeiwanManageView extends Vue {
    $refs: {
        mapDialog: SDialog
    }


}
</script>
