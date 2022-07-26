module.exports = {
  lintOnSave: false,
  devServer: {
    // 防止控制台报错 localhost:8080无法连接
    port: 8024,
    disableHostCheck: true
  },
  transpileDependencies: ['@dcloudio/uni-ui', 'uview-ui']
}
