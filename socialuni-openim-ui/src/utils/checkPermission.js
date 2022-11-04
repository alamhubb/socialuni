import permision from "@/js_sdk/wa-permission/permission.js";
import { toast } from "@/common/toast";
let isIos = false;
// #ifdef APP-PLUS
isIos = plus.os.name === "iOS" ? true : false;
// #endif
//相册
export async function checkPhotoLibraryPermission() {
  let status = null;
  if (isIos) {
    status = permision.judgeIosPermission("photoLibrary");
  } else {
    let r1 = await permision.requestAndroidPermission(
      "android.permission.READ_EXTERNAL_STORAGE"
    );
    let r2 = await permision.requestAndroidPermission(
      "android.permission.WRITE_EXTERNAL_STORAGE"
    );
    status = r1 === 1 && r2 === 1 ? 1 : -1;
  }
  status !== 1 ? gotoAppPermissionSetting("请打开相册权限") : "";
  return status === 1 ? true : false;
}
// 摄像头
export async function checkCameraPermission() {
  let status = null;
  if (isIos) {
    status = permision.judgeIosPermission("camera");
  } else {
    status = await permision.requestAndroidPermission(
      "android.permission.CAMERA"
    );
  }
  status !== 1 ? gotoAppPermissionSetting("请打开摄像头权限") : "";
  return status === 1 ? true : false;
}
// 麦克风
export async function checkRecordPermission() {
  let status = null;
  if (isIos) {
    status = permision.judgeIosPermission("record");
  } else {
    status = await permision.requestAndroidPermission(
      "android.permission.RECORD_AUDIO"
    );
  }
  status !== 1 ? gotoAppPermissionSetting("请打开麦克风权限") : "";
  return status === 1 ? true : false;
}
function gotoAppPermissionSetting(message) {
  toast(message);
  setTimeout(() => {
    permision.gotoAppPermissionSetting();
  }, 1000);
}
