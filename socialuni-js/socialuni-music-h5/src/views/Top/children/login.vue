<template>
	<el-dialog @open="openDialog" @close="dialogClose" v-model="dialogVisible" :append-to-body="true" :modal="false" :title="title" center top="30vh" width="25%">
		<section style="position: relative;">
<!--			<nav style="position: absolute; left: 0;top: -55px;" @click="change">-->
<!--				<el-link type="danger">{{is ? "扫码登录" : "账号登录"}}</el-link>-->
<!--			</nav>-->
			<div v-if="false">
				<el-form :model="formLabelAlign" label-position="left" label-width="60px">
					<el-form-item label="手机号">
						<el-input v-model="formLabelAlign.phone"></el-input>
					</el-form-item>
					<el-form-item label="验证码">
						<el-input v-model="formLabelAlign.password" placeholder="请输入验证码">
							<template #append>
								<span v-if="isShow" @click="sendCode">发送</span> <span v-else>{{time}}秒</span>
							</template>
						</el-input>
					</el-form-item>
				</el-form>
				<div style="text-align: center;" @click="login">
					<el-button type="primary">登陆</el-button>
				</div>
			</div>
			<!--      扫码登录-->
			<div v-else style="display: flex;justify-content: center;text-align: center;">
				<div>
					<el-image :src="image" style="width: 250px; height: 250px;" @click="loadingImg">
						<template #error>
							<div class="image-slot" style="width: 100%;height: 100%;display: flex;align-items: center; background: #f1ecec;">
								<el-skeleton :animated="true">
									<template #template>
										<el-skeleton-item style="width: 250px; height: 250px;" variant="image"/>
									</template>
								</el-skeleton>
							</div>
						</template>
					</el-image>
					<br>
					<div>请使用网易云App扫码登录!</div>
				</div>
			</div>
		</section>
	</el-dialog>
</template>
<script setup>
import { reactive, ref, shallowRef, toRefs } from "vue"
import { useStore } from "vuex";
import eventBus from "@/utlis/eventbus.js"
import { ElMessage } from "element-plus"
import { getKey, createLogin, checkLogin, getInfo, getPhoneCode } from "@/network/login.js"


const store = useStore ()

let title = ref ("登录")
let image = ref ("")
let is = ref (true)
let timer
const change = () => {
	is.value = !is.value
	if (!is.value) {

	}
}

const dialogClose = () => {
	clearInterval(timer)
}

let formLabelAlign = reactive ({
	phone: undefined, password: undefined
})

//
let isShow = shallowRef (true)
let time = shallowRef (59)
const sendCode = async () => {
	if (formLabelAlign.phone) {
		time.value = 59
		isShow.value = false
		await getPhoneCode (formLabelAlign.phone)
		let timer = setInterval (() => {
			time.value -= 1
			if (time.value === 0) {
				isShow.value = true
				clearInterval (timer)
			}
		}, 1000)
	}
}


//加载
const loadingImg = getLoginImg

async function getLoginImg () {
	let res = await getKey ()
	let key = res.data.data.unikey
	let result = await createLogin (key)
	image.value = `https://api.pwmqr.com/qrcode/create/?url=${result.data.data.qrurl}`
	timer = setInterval (() => {
		checkLogin (key).then (res => {
			title.value = res.data.message
			if (res.data.code === 803) {
				dialogVisible.value = false
				clearInterval (timer)
				ElMessage.success ({
					message: "登陆成功", type: "success"
				})
				getInfo ().then (res => {
					store.commit ("setUser", res.data.profile)
				})
			}
			else if (res.data.code === 800) {
				clearInterval (timer)
				image.value = ""
			}
			else if (res.data.code === 802) {
				image.value = res.data.avatarUrl
			}
		})
	}, 2000)
}


let dialogVisible = ref (false)

eventBus.on ("login", () => {
	clearInterval (timer)
	dialogVisible.value = true
	getLoginImg ()
})

const openDialog = () => {
	getLoginImg ()
}


const login = () => {
	dialogVisible.value = false
	clearInterval (timer)
	store.dispatch ("login", {phone:formLabelAlign.phone,captcha:formLabelAlign.password}).then (() => {
		store.dispatch ("getUserNumber")
		eventBus.emit ("login1")
	})
}
defineExpose ({
	dialogVisible
})
</script>
<style lang="less" scoped>
.el-button--primary{
	width: 50%;
}
</style>
