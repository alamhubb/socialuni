<template>
  <view class="setting">
    <view class="statusBar"></view>

    <u-form
      class="loginForm"
      labelPosition="top"
      labelWidth="auto"
      :model="config"
      :rules="rules"
      :labelStyle="{
        fontWeight: 400,
        marginTop: '20rpx',
        marginBottom: '10rpx',
      }"
      ref="loginForm"
    >
      <u-form-item label="请输入服务器地址" prop="addr" borderBottom>
        <u-input
          v-model="config.addr"
          border="none"
          fontSize="28rpx"
          placeholder="请输入服务器地址"
          clearable
        >
        </u-input>
      </u-form-item>
      <u-form-item label="登录注册服务器地址" prop="api_addr" borderBottom>
        <u-input
          v-model="config.api_addr"
          border="none"
          fontSize="28rpx"
          placeholder="登录注册服务器地址"
          clearable
        >
        </u-input>
      </u-form-item>
      <u-form-item label="IM API服务器地址" prop="api_addr2" borderBottom>
        <u-input
          v-model="config.api_addr2"
          border="none"
          fontSize="28rpx"
          placeholder="IM API服务器地址"
          clearable
        >
        </u-input>
      </u-form-item>
      <u-form-item label="IM WS地址" prop="ws_addr" borderBottom>
        <u-input
          v-model="config.ws_addr"
          border="none"
          fontSize="28rpx"
          placeholder="IM WS地址"
          clearable
        >
        </u-input>
      </u-form-item>
      <u-form-item label="图片存储" prop="object_storage" borderBottom>
        <u-input
          v-model="config.object_storage"
          border="none"
          fontSize="28rpx"
          placeholder="图片存储"
          clearable
        >
        </u-input>
      </u-form-item>
    </u-form>
    <view class="bottom">
      <u-alert title="重启app后配置生效" type="error" />
    </view>
    <view class="bottom">
      <u-button type="primary" @click="confirm"> 保存 </u-button>
    </view>
  </view>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      rules: {
        addr: [
          {
            type: "string",
            required: true,
            message: "请输入服务器地址",
            trigger: ["change"],
          },
        ],
        api_addr: [
          {
            type: "string",
            required: true,
            message: "请输入登录注册服务器地址",
            trigger: ["change"],
          },
        ],
        api_addr2: [
          {
            type: "string",
            required: true,
            message: "请输入IM API服务器地址",
            trigger: ["change"],
          },
        ],
        ws_addr: [
          {
            type: "string",
            required: true,
            message: "请输入IM WS地址",
            trigger: ["change"],
          },
        ],
        object_storage: [
          {
            type: "string",
            required: true,
            message: "请输入图片存储路径",
            trigger: ["change"],
          },
        ],
      },
      config: {
        addr: "",
        api_addr: "",
        api_addr2: "",
        ws_addr: "",
        object_storage: "",
      },
    };
  },
  methods: {
    init() {
      const { addr, api_addr, api_addr2, ws_addr, object_storage } =
        this.apiConfig;
      this.config.addr = addr;
      this.config.api_addr = api_addr;
      this.config.api_addr2 = api_addr2;
      this.config.ws_addr = ws_addr;
      this.config.object_storage = object_storage;
    },
    confirm() {
      this.$refs.loginForm.validate().then((valid) => {
        if (valid) {
          this.$store.commit("user/set_apiConfig", this.config);
          this.$toast("重启app后配置生效");
        }
      });
    },
  },
  onLoad() {
    this.init();
  },
  computed: {
    ...mapGetters(["apiConfig"]),
  },
};
</script>
<style lang="scss" scoped>
.setting {
  padding: 0 24rpx;
  .bottom {
    margin-top: 24rpx;
  }
}
</style>