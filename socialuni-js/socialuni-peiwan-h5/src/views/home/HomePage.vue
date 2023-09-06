<template>
    <div class="h100p">

    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import {DialogInterface} from "@/interface/DialogInterface";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import FileUtilH5 from "@socialuni/socialuni-native-h5/src/util/FileUtilH5";
import type DomFile from "@socialuni/socialuni-native-util/src/model/DomFile";
import ImgUtilH5 from "@socialuni/socialuni-native-h5/src/util/ImgUtilH5";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import CosService from "@socialuni/socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@/utils/UUIDUtil";
import SocialuniPeiwanAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import {useMagicKeys} from '@vueuse/core'
import {watch} from "vue";
import AlertUtil from "@socialuni/socialuni-native-h5/src/util/AlertUtil";


@Component({
    components: {SDialog, Plus}
})
export default class PeiwanManageView extends Vue {
    $refs: {
        mapDialog: SDialog
    }


}
</script>
