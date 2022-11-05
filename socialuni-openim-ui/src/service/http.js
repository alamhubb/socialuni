import store from "@/plugins/openIm/store";
function request(method, url, data) {
  return new Promise((resolve, reject) => {
    const apiConfig = store.getters.apiConfig;
    uni.request({
      url: apiConfig.api_addr2 + url,
      data,
      method,
      header: getHeader(),
      success(res) {
        resolve(res.data);
      },
      fail(err) {
        reject(err);
      },
    });
  });
}
export function get(url, data = {}) {
  return request("GET", url, data);
}
export function post(url, data = {}) {
  return request("POST", url, data);
}
function getHeader() {
  return {};
}
