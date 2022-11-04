<template>
  <view class="applicationList">
    <ContactUserCard
      class="ContactUserCard"
      v-for="(item, index) in list"
      :key="index"
      :card="item"
      :type="Number(type)"
      @refresh="init"
    >
    </ContactUserCard>
  </view>
</template>

<script>
import ContactUserCard from "@/components/ContactUserCard";
import { mapGetters } from "vuex";
export default {
  components: { ContactUserCard },
  data() {
    return {
      type: "0",
    };
  },
  methods: {
    init() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_friendNoticeList", this.$im);
      this.$store.dispatch("contacts/get_selfFriendNoticeList", this.$im);
      //#endif
    },
  },
  computed: {
    ...mapGetters([
      "operationID",
      "friendNoticeList",
      "selfFriendNoticeList",
      "userID",
    ]),
    title() {
      return this.type === "0" ? "好友请求" : "我的请求";
    },
    list() {
      return this.type === "0"
        ? this.friendNoticeList
        : this.selfFriendNoticeList;
    },
  },
  onLoad(params) {
    this.type = params.type;
    uni.setNavigationBarTitle({
      title: this.title,
    });
  },
};
</script>
<style lang="scss" scoped>
$pagePadding: 44rpx;
.applicationList {
  background-color: #f8f8f8;
  min-height: 100vh;
}
</style>