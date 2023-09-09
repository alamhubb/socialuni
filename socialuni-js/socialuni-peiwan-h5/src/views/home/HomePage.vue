<template>
    <div class="h100p">
        <div class="mx-sm pb-60">
            <div v-for="peiwan in peiwanList" class="shadow pd-sm bd-radius-10 mt-sm bg-white">
                <div class="flex-row">
                    <img class="flex-none size50 bd-radius" :src="peiwan.avatar">
                    <div class="flex-1 mx-sm flex-col">
                        <div class="row-between-center flex-none">
                            <div class="font-bold">Rosa</div>

                            <div class="row-col-center">
                                <div class="size4 bd-round bg-green"></div>
                                <div class="font-12 ml-xs">北京</div>
                                <div class="font-12 ml-xs">50km</div>
                            </div>
                        </div>

                        <div class="row-col-center row-wrap">
                            <div class="q-tag-blue">
                                电竞
                            </div>
                            <div class="q-tag-green ml-xs">
                                吃饭
                            </div>
                            <div class="q-tag-purple ml-xs">
                                电影
                            </div>
                            <div class="q-tag-orange ml-xs">
                                小酌
                            </div>
                        </div>

                        <div>
                            <div class="flex-1">

                            </div>
                        </div>

                        <div class="row-between-center">
                            <div class="row-col-end line-h1">
                                <div class="color-orange font-18 font-bold">
                                    600
                                </div>
                                <div class="font-12 ml-nm mb-nn">币/3小时</div>
                            </div>
                            <div class="row-col-end use-click font-12 line-h1">下单</div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import SocialuniPeiwanAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";

@Component({
    components: {SDialog, Plus}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }


    peiwanList: PeiwanRO[] = []

    created() {
        this.queryPeiwanInfoListAPI()
    }

    async queryPeiwanInfoListAPI() {
        const res = await SocialuniPeiwanAPI.queryPeiwanInfoListAPI()
        this.peiwanList = res.data
    }

}
</script>
