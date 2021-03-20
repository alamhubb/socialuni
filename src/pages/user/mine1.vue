<template>
  <view class="h100p flex-col">
    <u-navbar :is-back="false" title-bold class="flex-none">
      <view class="flex-row w100vw flex-auto">
        <view class="ml text-bold text-lg w100p">
          欢迎登录 - {{ $appName }}
        </view>
      </view>
    </u-navbar>

    <view class="flex-1 pt-30vh">
      <view class="h35vh col-between">
        <view class="u-type-warning-light-bg row-col-center mx px-sm py-xs mb-lg">
          <q-icon size="36" icon="volume-fill" class="u-type-warning"></q-icon>
          <view class="ml-ssm font-lg flex-1 col-row-center u-type-primary">
            <view>未登录，无法授权</view>
            <view>请点击登录按钮进行授权</view>
          </view>
        </view>

        <view class="flex-row px">
          <u-button class="w30vw">不授权并返回</u-button>
          <u-button class="w30vw text-bold" type="success">登录并授权</u-button>
        </view>
      </view>
    </view>


    <!--    <view class="flex-1 px pt-sm pb-10px col-between h100p">
          &lt;!&ndash;
          有用户时，百分百显示。绑定手机号时要显示
          或者就是为app时,app没用户登录时要显示
          &ndash;&gt;
          <view class="h100px row-center">
            &lt;!&ndash;        登录界面，展示logo&ndash;&gt;
            <image class="radius flex-none h100p"
                   mode="aspectFit"
                   src="/static/img/logo.png"
            />
          </view>

          <view class="flex-1">
            <view class="mt-xs px text-bold row-center row-grid">
              <u-checkbox v-model="contractChecked" active-color="#19be6b">
                我已阅读并同意
              </u-checkbox>
              <navigator :url="userAgreementUrl" class="text-blue">
                《用户协议》
              </navigator>
              <navigator :url="userPrivacyUrl" class="text-blue">
                《隐私政策》
              </navigator>
              <navigator :url="childProtectUrl" class="text-blue">
                《儿童个人信息保护规则及监护人须知》
              </navigator>
            </view>

            <view>
              &lt;!&ndash;          已登录，绑定手机号&ndash;&gt;
              <view v-if="user" class="mt-sm cu-bar bg-white row-around px">
                <button class="cu-btn bd-gray radius w35vw bg-white" @click="cancelBind">暂不绑定</button>
                <view @click="loginByPhoneNumAndBindPhoneNum">
                  <button class="cu-btn bg-orange-plain radius w35vw">
                    绑定
                  </button>
                </view>
              </view>

    &lt;!&ndash;          已登录用户绑定手机号，
              使用手机号登录，
              直接登录，
              授权登录
              授权绑定手机号，
              跳转过来用户没绑定手机号，如果未登录，提示登录，如果已登录，提示授权，未绑定手机号提示绑定手机号，已绑定提示授权。
              提供返回按钮，无论怎样，如果是第三方跳转过来的始终显示一个返回应用&ndash;&gt;

              &lt;!&ndash;          未登录，使用手机号登录&ndash;&gt;
              <view v-else>
                <view v-if="!phoneNumFocus && !authCodeFocus" class="mt-xs col-row-center">
                  &lt;!&ndash; 只要不为QQ小程序平台都可以使用微信登录&ndash;&gt;
                  <button class="bg-gradual-wx bd-none mt-sm bg-active round w80vw row-all-center"
                          @click="wxLogin">
                    <q-icon color="white" icon="weixin-fill" size="20" class="mr-xs"></q-icon>
                    微信登录
                  </button>

                  <button class="bg-gradual-qq bd-none mt bg-active round w80vw row-all-center"
                          @click="qqLogin">
                    <q-icon color="white" icon="qq-fill" size="18" class="mr-xs"></q-icon>
                    QQ登录
                  </button>
                </view>
                <view class="mt px">
                  <view class="row-center">
                    &lt;!&ndash;          微信颜色&ndash;&gt;
                    <view @click="loginByPhoneNumAndBindPhoneNum" v-if="showPhoneView">
                      <button :disabled="loginButtonDisabled"
                              class="bg-gradual-phone text-white text-lgg bd-none bg-active round w80vw">
                        <q-icon color="white" icon="mdi-cellphone-android" size="34" class="mr-sm"></q-icon>
                        手机号登录
                      </button>
                    </view>
                    <view v-else class="row-all-center" @click="showPhoneView=!showPhoneView">
                      <view class="q-solids-bottom text-gray">
                        其他登录方式
                      </view>
                      <q-icon class="ml-xs text-gray" icon="arrow-right"></q-icon>
                    </view>
                  </view>
                </view>
              </view>
            </view>
          </view>
        </view>
        <view class="row-between px flex-none mb-sm">
          <navigator :url="homeUrl" class="text-bold u-type-info q-solids-bottom">
            <q-icon icon="home" class="mr-xs"></q-icon>
            关于我们
          </navigator>

          <view @click="copyServiceNum" class="text-bold u-type-info q-solids-bottom">
            客服微信、QQ：{{ qqService }}
            <q-icon icon="attach" class="ml-xs"></q-icon>
          </view>
        </view>-->
  </view>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-property-decorator'
import {namespace} from 'vuex-class'
import QIcon from "@/components/q-icon/q-icon.vue";

const userStore = namespace('user')
@Component({
  components: {QIcon}
})
export default class LoginAll extends Vue {
}
</script>
