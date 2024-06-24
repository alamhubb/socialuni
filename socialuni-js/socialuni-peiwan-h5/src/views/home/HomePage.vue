<template>
    <div class="h100p">
        <q-scrollbar>
            {{ testwi }}---
            {{ windowWidth }}
            <div class="mx-sm pb-60" :style="{'column-count': columnNum}">
                <div v-for="user in peiwanList" class="shadow bg-white overflow-hidden use-click" @click="openKefu">
                    <img class="bd-radius w100p" style="max-height: 400px"
                         :src="user.imgs[0].src">
                    <!--   <div>

                     <div class="px-10">
                         <div class="row-between" @click="showUserChange(user)">
                           <div class="row-col-center" :class="{'color-red':user.vipFlag}">
                             {{ user.nickname }}
                           </div>
                           &lt;!&ndash;                    <div class="row-col-center">
                                                 <q-icon size="32" icon="mdi-circle-medium" :class="[getOnlineColor(user)]"></q-icon>
                                                 <span v-if="user.onlineFlag" class="text-gray ml-3 text-sm">在线</span>
                                                 <span v-else class="text-gray ml-3 text-sm">{{user.lastOnlineTime|formatTime}}</span>
                                               </div>&ndash;&gt;
                         </div>
                         &lt;!&ndash;                  <div class="row-between">
                                             <div>
                                               &lt;!&ndash;                展示列表和你喜欢的&ndash;&gt;
                                               &lt;!&ndash;                颜值只用来规范你能不能喜欢人家，你可以看到所有人，简单点来，先只算颜值，啥也不按 就按时间排序，在线时间&ndash;&gt;
                                               &lt;!&ndash;                年龄vip分数&ndash;&gt;
                                               <div class="cu-tag sm radius text-sm"
                                                     :class="[getGenderBgColor(user)]">
                                                 {{user.age}}
                                                 <q-icon class="row-col-start ml-2" size="24"
                                                         :icon="getGenderIcon(user)"/>
                                               </div>
                                               <div v-if="user.vipFlag" class="cu-tag radius bg-red sm text-sm text-bold" @click="openVip">VIP
                                               </div>
                                               <div class="cu-tag bg-orange radius sm text-sm" @click.stop="toFaceValuePage">
                                                 颜值:{{user.faceRatio}}
                                               </div>
                                             </div>
                                             <div>
                                               <q-icon addClass="mdi-rotate-90" size="24" icon="more-dot-fill" @click="openReportDialog"></q-icon>
                                             </div>
                                           </div>&ndash;&gt;
                       </div>

                    </div>-->
                    <!--div class="flex-row">
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
                    </div>-->
                </div>
            </div>
        </q-scrollbar>
    </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SScrollbar from "qing-ui-h5/src/components/QScrollbar.vue";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import SocialuniPeiwanAPI from "socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import AlertUtil from "qing-compat-js-h5/src/util/AlertUtil";
import apiData from "./res.json"
import {ElMessageBox} from "element-plus";
import {reactive} from "vue";
import {socialuniSystem} from "@/views/home/SocialuniSystemModule";

export const testm = reactive({window: 0})

@toNative
@Component({
    components: {SDialog, Plus, SScrollbar}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }


    peiwanList: PeiwanRO[] = []

    created() {

        this.queryPeiwanInfoListAPI()
    }

    get testwi() {
        return socialuniSystem.windowWidth
    }

    get columnNum() {
        console.log(socialuniSystem.windowWidth)
        if (socialuniSystem.windowWidth >= 668) {
            return 3
        } else {
            return 2
        }
    }

    openKefu() {
        const date = new Date().toLocaleDateString()
        ElMessageBox.alert(
            `
          <div>

<div class="font-bold font-16 color-main">联系客服下单，客服微信：491369310</div>
          <div class="row-all-center">
          <img class="size300" src="https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/peiwan/wanwei/bjwanwei.jpg"/>
</div>
          </div>
          `,
            `${date}日更新`,
            {
                dangerouslyUseHTMLString: true,
            }
        )
    }

    /*get windowWidth() {
      return socialuniSystemModule.windowWidth
    }*/

    windowWidth = 0

    mounted() {
        window.addEventListener('resize', () => {
            this.windowWidth = window.innerWidth
            testm.window = window.innerWidth
            // socialuniSystem.windowWidth = window.innerWidth
            // socialuniSystem.windowHeight = window.innerHeight
            // console.log(socialuniSystem.windowWidth)
        })
    }


    async queryPeiwanInfoListAPI() {
        const res = apiData
        console.log(res)
        // const res = await SocialuniPeiwanAPI.queryPeiwanInfoListAPI()
        this.peiwanList = res.data
    }

}
</script>
