export function toast(title, icon = "none", duration = 2000) {
  uni.showToast({
    title,
    icon,
    duration,
  });
}
