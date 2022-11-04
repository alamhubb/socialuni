import { toast } from "@/common/toast";
import store from "@/store";
export function scan(sessionType = "0", im) {
  //0、两种都搜索，1、单聊，2、群聊
  // #ifdef APP-PLUS
  uni.scanCode({
    scanType: ["qrCode"],
    success: (res) => {
      if (sessionType === "2") {
        checkGroup(res.result, sessionType, im);
      } else {
        checkUser(res.result, sessionType, im);
      }
    },
  });
  // #endif
}
function checkUser(id, sessionType, im) {
  im.getUsersInfo(store.getters.operationID, [id], (res) => {
    const list = JSON.parse(res.data) || [];
    if (
      res.errCode === 0 &&
      (sessionType === "0" || sessionType === "1") &&
      list.length
    ) {
      uni.navigateTo({
        url: "/pages/friend/info?id=" + id,
      });
    } else if (sessionType === "0") {
      checkGroup(id, sessionType, im);
    } else {
      toast("未查找到该用户");
    }
  });
}
function checkGroup(id, sessionType, im) {
  im.getGroupsInfo(store.getters.operationID, [id], (res) => {
    const list = JSON.parse(res.data) || [];
    if (
      res.errCode === 0 &&
      (sessionType === "0" || sessionType === "2") &&
      list.length
    ) {
      uni.navigateTo({
        url: "/pages/group/info?id=" + id + "&pageStatus=search",
      });
    } else if (sessionType === "0") {
      toast("未查找到该用户或群组");
    } else {
      toast("未查找到该群组");
    }
  });
}
