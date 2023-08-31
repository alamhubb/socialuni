/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const validMap = ['admin', 'editor']
  return validMap.indexOf(str.trim()) >= 0
}

export function validateUsername(rule, value, callback) {
  if (!validUsername(value)) {
    callback(new Error('请输入有效的登录账号'))
  } else {
    callback()
  }
}

export function validatePassword(rule, value, callback) {
  if (value.length < 5) {
    callback(new Error('密码长度不能小于5位!'))
  } else {
    callback()
  }
}
