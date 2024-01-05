<template>
    <q-dialog ref="userEditDialog" title="编辑用户信息" width="500px" :confirm="socialuniUserEditViewService.saveUser()">
        <div v-if="socialuniUserEditViewService.editUser" class="row-center">
            <el-form :model="socialuniUserEditViewService.editUser" label-width="70px">
                <el-form-item label="头像">
                    <div class="row-col-end">

                        <el-dropdown v-if="socialuniUserEditViewService.mineUser" trigger="click">
                            <el-avatar size="large" class="bd" shape="square" :src="socialuniUserEditViewService.editUser.avatar"/>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click.native="socialuniUserEditViewService.uploadUserAvatarImg">上传头像</el-dropdown-item>
                                    <el-dropdown-item divided @click.native="socialuniUserEditViewService.randomGenerateAvatar">随机生成头像
                                    </el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input class="w200" v-model.trim="socialuniUserEditViewService.editUser.nickname" clearable maxlength="6"/>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="socialuniUserEditViewService.editUser.gender">
                        <el-radio v-for="gender in socialuniUserEditViewService.genders" :label="gender.value">{{ gender.label }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-date-picker
                            class="w200"
                            v-model="socialuniUserEditViewService.editUser.birthday"
                            value-format="YYYY-MM-DD"
                            type="date"
                    />
                </el-form-item>
                <el-form-item label="所在地222">
                    <el-input class="w200" v-model.trim="socialuniUserEditViewService.editUser.city" clearable maxlength="10"/>
                </el-form-item>
            </el-form>
        </div>
    </q-dialog>
</template>

<script lang="ts" setup>
import QDialog from "qing-ui-h5/src/components/QComponents/QDialog.vue";

import socialuniUserEditViewService from "socialuni-user-sdk/src/logic/SocialuniUserEditViewService";
import {getCurrentInstance} from "vue";

socialuniUserEditViewService.initService(getCurrentInstance())
</script>
