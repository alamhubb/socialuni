import store from "@/plugins/openIm/store";
import { post } from "../http";
export function app_login(data) {
  const d = {
    ...data, //phoneNumber,password
    platform: store.getters.platform,
    operationID: store.getters.operationID,
  };
  // console.log(d);
  return post("/demo/login", d);
}
export function app_sendCode(data) {
  return post("/demo/code", data);
}
export function app_verifyCode(data) {
  return post("/demo/verify", data);
}
export function app_register(data) {
  return post("/demo/password", data);
}
export function app_resetPassword(data) {
  return post("/demo/reset_password", data);
}
