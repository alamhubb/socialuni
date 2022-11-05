import {Pinia, Store} from "pinia-class-component"
import OpenImUserAPI from "@/api/OpenImUserAPI";

@Store
export default class OpenImUserModule extends Pinia {
    platform: number = null
    operationID = ""
    userID = "3493949860"
    token = ""
    userInfo = {
        userID: "3493949860",
        nickname: "blooming",
        faceURL: "ic_avatar_02",
        gender: 1,
        phoneNumber: "",
        birth: 0,
        email: "",
        createTime: 0,
        ex: "",
        attachedInfo: "",
    }
    systemInfo = {
        screenHeight: 667,
        screenWidth: 375,
    }
    loginStatus = false
    //是否登录
    loginUserInfo = null

    connectStatus = 1
    //服务器连接状态:0,:失败;1:成功;2:连接中
    apiConfig = {
        addr: "121.37.25.71", //服务器地址
        // api_addr: "http://43.128.5.63:10002", //api域名地址
        // api_addr2: "http://43.128.5.63:10004", //api域名地址
        // ws_addr: "ws://43.128.5.63:10001", //websocket地址
        api_addr: "http://121.37.25.71:10002", //api域名地址
        api_addr2: "http://121.37.25.71:10004", //api域名地址
        ws_addr: "ws://121.37.25.71:10001", //websocket地址
        // object_storage: "cos", //选择存储
        object_storage: "minio", //选择存储
    }

    set_systemInfo(systemInfo) {
        this.systemInfo = systemInfo;
    }

    set_platform(platform) {
        this.platform = platform;
    }

    set_operationID(operationID) {
        this.operationID = operationID;
    }

    set_userID(userID = "") {
        this.userID = userID;
    }

    set_token(token = "") {
        this.token = token;
    }

    set_userInfo(userInfo) {
        this.userInfo = userInfo;
    }

    set_loginUserInfo(loginUserInfo = null) {
        this.loginUserInfo = loginUserInfo;
    }

    set_loginStatus(loginStatus = false) {
        this.loginStatus = loginStatus;
    }

    set_connectStatus(connectStatus = 0) {
        this.connectStatus = connectStatus;
    }

    set_apiConfig(config) {
        this.apiConfig = config;
    }

    login({commit}, userInfo) {
        return new Promise((resolve, reject) => {
            OpenImUserAPI.app_login(userInfo)
                .then((res) => {
                    if (res.errCode === 0) {
                        commit("set_token", res.data.token);
                        commit("set_userID", res.data.userID);
                        resolve();
                    } else {
                        reject({message: res.errMsg});
                    }
                })
                .catch((error) => {
                    console.log(error);
                    reject(error);
                });
        });
    }

    logOut({commit, rootGetters}, im) {
        commit("set_token");
        commit("set_userID");
        commit("set_loginStatus");
        if (rootGetters.operationID && im) {
            im.logout(rootGetters.operationID, () => {
            });
        }
        // uni.clearStorage();
        uni.redirectTo({
            url: "/pages/login/index",
        });
    }
}
