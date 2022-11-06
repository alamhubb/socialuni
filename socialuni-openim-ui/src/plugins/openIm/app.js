import { toast } from "@/common/toast";
import { commonConfig } from "@/index";
import store from "@/plugins/openIm/store";
// const isDev = process.env.NODE_ENV === "development";
const im = uni.requireNativePlugin("Tuoyun-OpenIMSDK");
const event = uni.requireNativePlugin("globalEvent");
export function init() {
  init_platform();
  createDir().then((data_dir) => {
    const apiConfig = store.getters.apiConfig;
    const flag = im.initSDK(store.getters.operationID, {
      platform: store.getters.platform,
      api_addr: apiConfig.api_addr,
      ws_addr: apiConfig.ws_addr,
      data_dir, //SDK数据存放目录
      log_level: commonConfig.log_level,
      object_storage: apiConfig.object_storage,
    });
    setListener();
    addListener();
    checkCurrenUser();
  });
}
//初始化platform
function init_platform() {
  const systemInfo = uni.getSystemInfoSync();
  store.commit("user/set_systemInfo", systemInfo);
  store.commit("user/set_platform", systemInfo.platform === "android" ? 2 : 1);
  store.commit("user/set_operationID", systemInfo.deviceId);
  // store.commit("user/set_operationID", Date.now().toString());
}
//创建存储目录
function createDir() {
  return new Promise((resolve, reject) => {
    plus.io.requestFileSystem(plus.io.PRIVATE_DOC, (fs) => {
      fs.root.getDirectory(
        "user",
        {
          create: true,
        },
        (entry) => {
          resolve(entry.fullPath);
        },
        (error) => {
          reject();
        }
      );
    });
  });
}
// 设置监听
function setListener() {
  im.setUserListener((res) => {
    console.log("setUserListener", res);
  });
  im.setFriendListener((res) => {
    console.log("setFriendListener", res);
  });
  im.setGroupListener((res) => {
    console.log("setGroupListener", res);
  });
  im.setAdvancedMsgListener((res) => {
    console.log("setAdvancedMsgListener", res);
  }); //收到新消息
  im.setConversationListener((res) => {
    console.log("setConversationListener", res);
  }); //会话列表变化
}
function addListener() {
  // connect status
  event.addEventListener("onConnectSuccess", (res) => {
    console.log("onConnectSuccess", res);
    const connectStatus = store.getters.connectStatus;
    if (connectStatus !== 1) {
      store.commit("user/set_connectStatus", 1);
    }
    // checkCurrenUser();
  });
  event.addEventListener("onConnectFailed", (res) => {
    console.log("onConnectFailed", res);
    const connectStatus = store.getters.connectStatus;
    const { errCode } = res;
    const kickedArr = [706, 1001];
    if (kickedArr.includes(errCode)) {
      toast("该账号已在另一设备登录");
      setTimeout(() => {
        store.dispatch("user/logOut", im);
      }, 1000);
    } else if (connectStatus !== 0) {
      store.commit("user/set_connectStatus", 0);
      toast("服务器连接超时，请稍后再试");
    }
  });
  event.addEventListener("onConnecting", (res) => {
    console.log("onConnecting", res);
    const connectStatus = store.getters.connectStatus;
    if (connectStatus !== 2) {
      store.commit("user/set_connectStatus", 2);
    }
  });
  // sendMessage callback
  event.addEventListener("sendMessageProgress", (res) => {
    // console.log("sendMessageProgress", res);
    const currentUserID = store.getters.userID;
    store.dispatch("message/do_singleMessageStatusList", {
      message: JSON.parse(res.message),
      status: 0,
      currentUserID,
    });
  });
  event.addEventListener("sendMessageSuccess", (res) => {
    console.log("sendMessageSuccess", JSON.parse(res.message));
    store.commit("message/set_successTimes");
    const currentUserID = store.getters.userID;
    //status:   0：发送中,1：发送成功,-1:发送失败
    store.dispatch("message/do_singleMessageStatusList", {
      message: JSON.parse(res.message),
      status: 1,
      currentUserID,
    });
  });
  event.addEventListener("sendMessageFailed", (res) => {
    console.log("sendMessageFailed", res);
    const currentUserID = store.getters.userID;
    store.dispatch("message/do_singleMessageStatusList", {
      message: JSON.parse(res.message),
      status: -1,
      errCode: res.errCode,
      currentUserID,
    });
  });
  // message listener
  event.addEventListener("onRecvNewMessage", (res) => {
    const m = JSON.parse(res.message);
    console.log("onRecvNewMessage", m);
    if (m.contentType !== 113) {
      store.dispatch("message/set_newMessageTimes");
      store.dispatch("message/set_newMessageList", m);
      const currentUserID = store.getters.userID;
      const userID = currentUserID === m.sendID ? m.recvID : m.sendID;
      store.dispatch("message/push_localConversationMessage", {
        groupID: m.sessionType === 2 || m.sessionType === 3 ? m.groupID : "",
        userID: m.sessionType === 1 ? userID : "",
        messageItem: m,
        currentUserID,
        im,
      });
    }
  });
  event.addEventListener("onRecvMessageRevoked", (res) => {
    console.log("onRecvMessageRevoked", res);
    store.dispatch("message/set_revokeMessageList", res.msgId);
    store.dispatch("message/set_revokeMessageTimes");
    store.dispatch("message/set_newMessageTimes");
  });
  //标记单聊消息已读
  event.addEventListener("onRecvC2CReadReceipt", (res) => {
    console.log("onRecvC2CReadReceipt", res);
    const currentUserID = store.getters.userID;
    store.commit("message/set_localMessageAsRead", {
      list: JSON.parse(res.msgReceiptList),
      currentUserID,
    });
  });
  //标记群聊消息已读
  event.addEventListener("onRecvGroupReadReceipt", (res) => {
    console.log("onRecvGroupReadReceipt", res);
    const currentUserID = store.getters.userID;
    store.commit("message/set_localMessageAsRead", {
      list: JSON.parse(res.groupMsgReceiptList),
      currentUserID,
    });
  });
  //文件上传
  event.addEventListener("uploadFileFailed", (res) => {
    console.log("uploadFileFailed", res);
    toast("上传失败，请稍后再试");
  });
  event.addEventListener("uploadFileProgress", (res) => {
    console.log("uploadFileProgress", res);
  });
  event.addEventListener("uploadFileSuccess", (res) => {
    console.log("uploadFileSuccess", res);
    store.commit("message/set_uploadFileRes", res.data);
  });
  event.addEventListener("onJoinedGroupDeleted", (res) => {
    store.commit("contacts/set_joinedGroupDeletedTimes");
  });
  event.addEventListener("onBlackAdded", (res) => {
    store.commit("message/set_frinendChangeTimes");
  });
  event.addEventListener("onBlackDeleted", (res) => {
    store.commit("message/set_frinendChangeTimes");
  });
  event.addEventListener("onFriendInfoChanged", (res) => {
    console.log("onFriendInfoChanged", res);
    store.commit("message/set_frinendInfoChangeTimes");
  });
  event.addEventListener("onFriendApplicationAdded", () => {
    store.dispatch("contacts/get_friendNoticeList", im);
  });
  event.addEventListener("onFriendApplicationDeleted", () => {
    store.dispatch("contacts/get_friendNoticeList", im);
  });
  event.addEventListener("onGroupApplicationAdded", () => {
    store.dispatch("contacts/get_groupNoticeList", im);
  });
  event.addEventListener("onGroupApplicationDeleted", () => {
    store.dispatch("contacts/get_groupNoticeList", im);
  });
  // event.addEventListener("onNewConversation", (res) => {
  //   console.log("onNewConversation", res);
  // });
  // event.addEventListener("onConversationChanged", (res) => {
  //   console.log("onConversationChanged", res);
  // });
  // event.addEventListener("onTotalUnreadMessageCountChanged", (res) => {
  //   console.log("onTotalUnreadMessageCountChanged", res);
  // });
}
//上次没有退出登录-自动登录
function checkCurrenUser() {
  const userID = store.getters.userID;
  const token = store.getters.token;
  const loginStatus = store.getters.loginStatus;
  const connectStatus = store.getters.connectStatus;
  const hasLastLoginData = userID && token ? true : false; //是否有上次登录用户数据(userID、token)
  if (hasLastLoginData && connectStatus === 1 && !loginStatus) {
    startLogin();
  } else if (!hasLastLoginData) {
    store.dispatch("user/logOut", im);
  }
}
function startLogin() {
  const operationID = store.getters.operationID;
  login()
    .then(() => {
      im.getSelfUserInfo(operationID, (res) => {
        if (res.errCode === 0) {
          const loginStatus = store.getters.loginStatus;
          if (!loginStatus) {
            const data = JSON.parse(res.data);
            store.commit("user/set_userInfo", data);
            console.log(data);
            store.commit("user/set_loginStatus", true);
            store.dispatch("contacts/get_friendNoticeList", im);
            store.dispatch("contacts/get_selfFriendNoticeList", im);
            store.dispatch("contacts/get_groupNoticeList", im);
            store.dispatch("contacts/get_selfGroupNoticeList", im);
          }
        } else {
          toast(res.errMsg);
        }
      });
    })
    .catch((msg) => {
      toast(msg);
      store.dispatch("user/logOut", im);
    });
}
function login() {
  const operationID = store.getters.operationID;
  const userID = store.getters.userID;
  const token = store.getters.token;
  return new Promise((resolve, reject) => {
    im.login(operationID, userID, token, (res) => {
      if (res.errCode === 0) {
        const loginStatus = getLoginStatus();
        if (loginStatus) {
          resolve(loginStatus);
        } else {
          reject("登录失败");
        }
      } else {
        reject(res.errMsg);
      }
    });
  });
}
function getLoginStatus() {
  const status = im.getLoginStatus(); // 返回值为 101:登录成功 102:登陆中 103:登录失败 201:登出
  if (status === 101) {
    return true;
  } else {
    return false;
  }
}
init();
export default im;
