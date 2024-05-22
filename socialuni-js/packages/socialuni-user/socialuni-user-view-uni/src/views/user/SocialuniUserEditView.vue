<template>
    <div v-if="socialuniUserEditViewService.editUser" class="h100p">
        <div class="pt-0 bg-white solid-bottom mt-10vh">
            <div>
                <div class="cu-form-group">
                    <div class="title">
                        昵称
                    </div>
                    <uni-easyinput placeholder="昵称" maxlength="6" v-model.trim="socialuniUserEditViewService.editUser.nickname"></uni-easyinput>
<!--                    <input :cursor-spacing="20" placeholder="昵称" maxlength="6" v-model.trim="socialuniUserEditViewService.editUser.nickname"/>-->
<!--                    <div class="uni-icon uni-icon-clear" v-if="socialuniUserEditViewService.editUser.nickname" @click="clearNickname"></div>-->
                </div>

                <!-- 不为单性应用，且用户没修改过性别才可修改-->
                <!--        && !user.genderModified-->
                <div class="cu-form-group" v-if="socialuniUserEditViewService.appGenderType === socialuniUserEditViewService.GenderTypeAll">
                    <div class="title">性别</div>
                    <div>
                        <radio-group @change="socialuniUserEditViewService.genderChange" class="flex-1">
                            <label v-for="report in socialuniUserEditViewService.genders" :key="report.value">
                                <radio :value="report.value" :checked="report.value===socialuniUserEditViewService.editUser.gender"></radio>
                                <span class="mr-sm">{{ report.label }}</span>
                            </label>
                        </radio-group>
                    </div>
                </div>

                <div class="cu-form-group">
                    <div class="title">出生日期</div>
                    <picker mode="date" :value="socialuniUserEditViewService.editUser.birthday" start="1900-01-01" :end="socialuniUserEditViewService.endDate" @change="socialuniUserEditViewService.dateChange">
                        <div class="picker">
                            {{ socialuniUserEditViewService.editUser.birthday }}
                        </div>
                    </picker>
                </div>

                <div class="cu-form-group">
                    <div class="title">
                        所在地
                    </div>
                    <uni-easyinput placeholder="所在地" maxlength="10" v-model.trim="socialuniUserEditViewService.editUser.city"></uni-easyinput>
                </div>

                <!--        todo qq不支持联系方式，改为判断qq一件加好友-->
                <!--        <div class="cu-form-group">
                          <div class="title">
                            联系方式
                          </div>
                          <input :cursor-spacing="20" v-model="contactAccount" maxlength="30" placeholder="例如：vx:491369310"/>
                          <div class="uni-icon uni-icon-clear" v-if="contactAccount" @click="clearContactAccount"></div>
                        </div>-->
                <!--<div class="cu-form-group margin-top">
                    <div class="title">
                        个人介绍
                    </div>
                    <input placeholder="两字短标题" name="input"></input>
                    &lt;!&ndash;                展开下拉，可以显示 兴趣爱好职业收入等等，可自定义&ndash;&gt;
                </div>

                <div class="cu-form-group margin-top">
                    <div class="title">
                        理想型
                        &lt;!&ndash;                    同个人介绍相似&ndash;&gt;
                    </div>
                    <input placeholder="两字短标题" name="input"></input>
                </div>

                <div class="cu-form-group margin-top">
                    <div class="title">
                        微信
                        <q-icon icon="mdi-wechat"></q-icon>
                    </div>
                    <input placeholder="两字短标题" name="input"></input>
                </div>
                <div class="cu-form-group">
                    <div class="title">
                        QQ
                        <q-icon icon="mdi-qqchat"></q-icon>
                    </div>
                    <input placeholder="三字标题" name="input"></input>
                </div>

                <div class="cu-form-group">
                    <div class="title">
                        微博
                        <q-icon icon="mdi-sina-weibo"></q-icon>
                    </div>
                    <input placeholder="三字标题" name="input"></input>
                </div>
                -->
            </div>
        </div>
        <div class="cu-bar btn-group bg-white flex-none pt-5 pb-sm py-lg">
            <!--      <button class="cu-btn bd-gray radius bg-white" @click="closeUserEditPop">取消</button>-->
            <q-button info light md @click="socialuniUserEditViewService.goBack()" :disabled="socialuniUserEditViewService.btnDisabled">返回</q-button>
            <q-button success md @click="socialuniUserEditViewService.saveUser()" :disabled="socialuniUserEditViewService.btnDisabled">保存</q-button>
        </div>
    </div>
</template>

<script lang="ts">
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue"
import QIcon from "qing-ui/src/components/QIcon.vue"
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue"
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue"
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QButton from "qing-ui/src/components/QButton.vue";
import socialuniUserEditViewService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewService";
import {getCurrentInstance} from "vue";


@toNative
@Component({
  components: {
    SocialuniUserInfoImg,
    QIcon,
    QButton,
    QNavbar,
    SocialGenderTag,
  }
})
export default class SocialuniUserDetailView extends Vue {
  socialuniUserEditViewService = socialuniUserEditViewService

  created(){
    this.socialuniUserEditViewService.initService(this)
  }
}
</script>
