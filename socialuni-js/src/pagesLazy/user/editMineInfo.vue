<template>
  <view v-if="user" class="flex-col">
    <view class="pt-0 bg-white solid-bottom flex-auto overflow-auto">
      <view>
        <view class="cu-form-group">
          <view class="title">
            昵称
          </view>
          <input :cursor-spacing="20" placeholder="昵称" maxlength="6" v-model.trim="nickname"/>
          <view class="uni-icon uni-icon-clear" v-if="nickname" @click="clearNickname"></view>
        </view>

        <!-- 不为单性应用，且用户没修改过性别才可修改-->
        <!--        && !user.genderModified-->
        <view class="cu-form-group" v-if="appGenderType === GenderTypeAll">
          <view class="title">性别</view>
          <view>
            <radio-group @change="genderChange" class="flex-1">
              <label v-for="report in genders" :key="report.value">
                <radio :value="report.value" :checked="report.value===gender"></radio>
                <text class="mr-sm">{{ report.label }}</text>
              </label>
            </radio-group>
          </view>
        </view>

        <view class="cu-form-group">
          <view class="title">出生日期</view>
          <picker mode="date" :value="birthday" start="1900-01-01" :end="endDate" @change="dateChange">
            <view class="picker">
              {{ birthday }}
            </view>
          </picker>
        </view>

        <view class="cu-form-group">
          <view class="title">
            所在地
          </view>
          <input :cursor-spacing="20" v-model="city" maxlength="10" placeholder="所在地"/>
          <view class="uni-icon uni-icon-clear" v-if="city" @click="clearLocation"></view>
        </view>

        <!--        todo qq不支持联系方式，改为判断qq一件加好友-->
        <!--        <view class="cu-form-group">
                  <view class="title">
                    联系方式
                  </view>
                  <input :cursor-spacing="20" v-model="contactAccount" maxlength="30" placeholder="例如：vx:491369310"/>
                  <view class="uni-icon uni-icon-clear" v-if="contactAccount" @click="clearContactAccount"></view>
                </view>-->
        <!--<view class="cu-form-group margin-top">
            <view class="title">
                个人介绍
            </view>
            <input placeholder="两字短标题" name="input"></input>
            &lt;!&ndash;                展开下拉，可以显示 兴趣爱好职业收入等等，可自定义&ndash;&gt;
        </view>

        <view class="cu-form-group margin-top">
            <view class="title">
                理想型
                &lt;!&ndash;                    同个人介绍相似&ndash;&gt;
            </view>
            <input placeholder="两字短标题" name="input"></input>
        </view>

        <view class="cu-form-group margin-top">
            <view class="title">
                微信
                <q-icon icon="mdi-wechat"></q-icon>
            </view>
            <input placeholder="两字短标题" name="input"></input>
        </view>
        <view class="cu-form-group">
            <view class="title">
                QQ
                <q-icon icon="mdi-qqchat"></q-icon>
            </view>
            <input placeholder="三字标题" name="input"></input>
        </view>

        <view class="cu-form-group">
            <view class="title">
                微博
                <q-icon icon="mdi-sina-weibo"></q-icon>
            </view>
            <input placeholder="三字标题" name="input"></input>
        </view>
        -->
      </view>
    </view>
    <view class="cu-bar btn-group bg-white flex-none pt-5 pb-sm">
      <!--      <button class="cu-btn bd-gray radius bg-white" @click="closeUserEditPop">取消</button>-->
      <button class="cu-btn bg-green radius" @click="saveUser" :disabled="btnDisabled">保存</button>
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Emit, Vue, Watch } from 'vue-property-decorator'
import SocialuniUserAPI from '../../socialuni/api/socialuni/SocialuniUserAPI'
import { parseDate } from '../../socialuni/utils'
import JsonUtils from '../../socialuni/utils/ObjectUtil'
import AlertUtil from '../../socialuni/utils/AlertUtil'
import ToastUtil from '../../socialuni/utils/ToastUtil'
import { socialUserModule, socialUserStore } from '../../socialuni/store'
import CenterUserDetailRO from '../../socialuni/model/social/CenterUserDetailRO'
import EnumStrVO from '../../socialuni/constant/EnumStrVO'
import GenderType from '../../socialuni/constant/GenderType'
import UserEditVO from '../../socialuni/model/user/UserEditVO'
import BirthAgeUtil from '@/socialuni/utils/BirthAgeUtil'

@Component
export default class UserEdit extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  nickname = ''
  gender = GenderType.girl
  birthday = '1999-01-01'
  city = ''
  wxAccount = ''
  contactAccount = ''
  qqAccount = ''
  wbAccount = ''
  endDate = ''
  btnDisabled = false

  appGenderType = GenderType.all
  GenderTypeAll = GenderType.all

  genders: EnumStrVO [] = GenderType.userEditEnums

  created () {
    this.endDate = parseDate(new Date())
    this.initData()
  }

  initData () {
    if (this.user) {
      this.nickname = this.user.nickname || ''
      this.gender = this.user.gender || GenderType.girl
      this.birthday = this.user.birthday || '2001-01-01'
      this.city = this.user.city || ''
      // this.contactAccount = this.user.contactAccount || ''
      // this.wxAccount = this.user.wxAccount || ''
      // this.qqAccount = this.user.qqAccount || ''
      // this.wbAccount = this.user.wbAccount || ''
      this.btnDisabled = false
    }
  }

  @Watch('user')
  watchUserChange () {
    this.initData()
  }

  @Emit('close')
  closeUserEditPop () {
    return ''
  }

  clearNickname () {
    this.nickname = ''
  }

  clearLocation () {
    this.city = ''
  }

  clearWxAccount () {
    this.wxAccount = ''
  }

  clearContactAccount () {
    this.contactAccount = ''
  }

  clearQqAccount () {
    this.qqAccount = ''
  }

  clearWbAccount () {
    this.wbAccount = ''
  }

  async saveUser () {
    if (this.contactAccount) {
      if (this.contactAccount.length > 30) {
        AlertUtil.hint('联系方式不能超过30个字符，例如：vx:491369310')
        return
      } else if (this.contactAccount.length < 5) {
        AlertUtil.hint('联系方式必须大于4个字符，例如：vx:491369310')
        return
      }
    }

    //修改了性别
    if (this.user.gender !== this.gender) {
      const confirm = await AlertUtil.confirm('性别修改后不可再更改，请确认是否修改')
      if (!confirm) {
        return ToastUtil.toastLong('您选择了不修改性别')
      }
    }

    if (this.birthday && this.birthday.length > 4) {
      const age = BirthAgeUtil.getAgeByBirth(this.birthday)
      if (age < 18) {
        return AlertUtil.hint('年龄不能小于18岁')
      }
    }

    this.btnDisabled = true
    AlertUtil.confirm('是否确定修改个人信息').then(() => {
      const userCopy: UserEditVO = JsonUtils.deepClone(this.user)
      userCopy.nickname = this.nickname
      userCopy.gender = this.gender
      userCopy.birthday = this.birthday
      userCopy.city = this.city
      userCopy.contactAccount = this.contactAccount
      userCopy.wxAccount = this.wxAccount
      userCopy.qqAccount = this.qqAccount
      userCopy.wbAccount = this.wbAccount
      SocialuniUserAPI.editUserAPI(userCopy).then((res: any) => {
        socialUserModule.setUser(res.data)
        ToastUtil.toast('已修改')
        this.closeUserEditPop()
      }).finally(() => {
        this.btnDisabled = false
      })
    }).catch(() => {
      this.closeUserEditPop()
      this.btnDisabled = false
    })
  }

  dateChange ({ target }) {
    this.birthday = target.value
  }

  genderChange ({ target }) {
    this.gender = target.value
  }
}
</script>
