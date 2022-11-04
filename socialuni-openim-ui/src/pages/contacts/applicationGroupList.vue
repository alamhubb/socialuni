<template>
  <view class="applicationList">
    <ContactGroupCard
      class="ContactGroupCard"
      v-for="(item, index) in list"
      :key="index"
      :card="item"
      :type="Number(type)"
      @refresh="init"
    >
    </ContactGroupCard>
  </view>
</template>

<script>
import ContactGroupCard from "@/components/ContactGroupCard";
import { mapGetters } from "vuex";
export default {
  components: { ContactGroupCard },
  data() {
    return {
      type: "0",
    };
  },
  methods: {
    init() {
      //#ifdef APP-PLUS
      this.$store.dispatch("contacts/get_groupNoticeList", this.$im);
      this.$store.dispatch("contacts/get_selfGroupNoticeList", this.$im);
      //#endif
    },
  },
  computed: {
    ...mapGetters([
      "operationID",
      "groupNoticeList",
      "selfGroupNoticeList",
      "userID",
    ]),
    title() {
      return this.type === "0" ? "入群申请" : "我的申请";
    },
    list() {
      return this.type === "0"
        ? this.groupNoticeList
        : this.selfGroupNoticeList;
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